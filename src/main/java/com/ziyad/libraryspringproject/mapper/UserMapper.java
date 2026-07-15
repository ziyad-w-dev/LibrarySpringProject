package com.ziyad.libraryspringproject.mapper;

import com.ziyad.libraryspringproject.domain.dto.UserRequest;
import com.ziyad.libraryspringproject.domain.dto.UserResponse;
import com.ziyad.libraryspringproject.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest userRequest, String password){
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setUserName(userRequest.getUserName());
        user.setPassword(password);
        user.setRole(userRequest.getRole());

        return user;
    }

    public UserResponse toDto(User userEntity){
        UserResponse userResponse = UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getUserName())
                .role(userEntity.getRole())
                .build();

        return userResponse;
    }
}
