package com.ziyad.libraryspringproject.service.impl;

import com.ziyad.libraryspringproject.domain.dto.UserRequest;
import com.ziyad.libraryspringproject.domain.dto.UserResponse;
import com.ziyad.libraryspringproject.domain.entity.User;
import com.ziyad.libraryspringproject.exceptions.EmailAlreadyExistsException;
import com.ziyad.libraryspringproject.exceptions.UserNotFoundException;
import com.ziyad.libraryspringproject.mapper.UserMapper;
import com.ziyad.libraryspringproject.repository.UserRepository;
import com.ziyad.libraryspringproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse createUser(UserRequest userRequest){
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new EmailAlreadyExistsException("Email Already Exists!");
        }
        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());
        User userEntity = userMapper.toEntity(userRequest,hashedPassword);
        User savedUser = userRepository.save(userEntity);
        UserResponse userResponse = userMapper.toDto(savedUser);

        return userResponse;

    }

    @Override
    public User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found With id: "+ id));
    }

    public void userPartialUpdate(){

    }



}
