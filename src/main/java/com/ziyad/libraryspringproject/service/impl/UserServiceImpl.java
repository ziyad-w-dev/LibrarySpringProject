package com.ziyad.libraryspringproject.service.impl;

import com.ziyad.libraryspringproject.domain.dto.UpdateUserRequest;
import com.ziyad.libraryspringproject.domain.dto.UserRequest;
import com.ziyad.libraryspringproject.domain.dto.UserResponse;
import com.ziyad.libraryspringproject.domain.entity.Role;
import com.ziyad.libraryspringproject.domain.entity.User;
import com.ziyad.libraryspringproject.exceptions.EmailAlreadyExistsException;
import com.ziyad.libraryspringproject.exceptions.UnauthorizedActionException;
import com.ziyad.libraryspringproject.exceptions.UserNameAlreadyExistsException;
import com.ziyad.libraryspringproject.exceptions.UserNotFoundException;
import com.ziyad.libraryspringproject.mapper.UserMapper;
import com.ziyad.libraryspringproject.repository.UserRepository;
import com.ziyad.libraryspringproject.security.CustomUserDetails;
import com.ziyad.libraryspringproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public UserResponse createUser(UserRequest userRequest){
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new EmailAlreadyExistsException("Email Already Exists!");
        }
        if(userRepository.existsByUserName(userRequest.getUserName())){
            throw new UserNameAlreadyExistsException("UserName Already Exists!");
        }
        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());
        User userEntity = userMapper.toEntity(userRequest,hashedPassword);
        User savedUser = userRepository.save(userEntity);
        UserResponse userResponse = userMapper.toDto(savedUser);

        return userResponse;

    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse findUserById(Long id){
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        UserResponse userResponse = userMapper.toDto(user);

        return userResponse;
    }

    @Transactional
    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request){
        CustomUserDetails principal  = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long loggedInId = principal.getId();
        if(!loggedInId.equals(id)){
            throw new UnauthorizedActionException("You can only update your Account!");
        }

        User realUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        if(request.getEmail() != null){
            if(!realUser.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())){
                throw new EmailAlreadyExistsException("Email Already Exists!");
            }
            realUser.setEmail(request.getEmail());
        }
        if(request.getUserName() != null){
            if(!realUser.getUserName().equals(request.getUserName()) && userRepository.existsByUserName(request.getUserName())){
                throw new UserNameAlreadyExistsException("UserName Already Exists!");
            }
            realUser.setUserName(request.getUserName());
        }
        User savedUser = userRepository.save(realUser);
        UserResponse userResponse = userMapper.toDto(savedUser);

        return userResponse;
    }

    @Transactional
    @Override
    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void promoteToAdmin(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

}
