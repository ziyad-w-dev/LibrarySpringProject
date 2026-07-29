package com.ziyad.libraryspringproject.service;

import com.ziyad.libraryspringproject.domain.dto.UpdateUserRequest;
import com.ziyad.libraryspringproject.domain.dto.UserRequest;
import com.ziyad.libraryspringproject.domain.dto.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse findUserById(Long id);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    void deleteUser(Long id);

    void promoteToAdmin(Long id);
}
