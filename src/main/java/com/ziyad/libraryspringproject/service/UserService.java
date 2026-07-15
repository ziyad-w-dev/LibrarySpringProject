package com.ziyad.libraryspringproject.service;

import com.ziyad.libraryspringproject.domain.dto.UserRequest;
import com.ziyad.libraryspringproject.domain.dto.UserResponse;
import com.ziyad.libraryspringproject.domain.entity.User;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    User findUserById(Long id);
}
