package com.ziyad.libraryspringproject.service;

import com.ziyad.libraryspringproject.domain.dto.AuthResponse;
import com.ziyad.libraryspringproject.domain.dto.LoginRequest;

public interface AuthService {

    AuthResponse login(LoginRequest request);
}
