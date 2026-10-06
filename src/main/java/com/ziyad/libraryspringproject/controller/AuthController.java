package com.ziyad.libraryspringproject.controller;

import com.ziyad.libraryspringproject.domain.dto.AuthResponse;
import com.ziyad.libraryspringproject.domain.dto.LoginRequest;
import com.ziyad.libraryspringproject.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}
