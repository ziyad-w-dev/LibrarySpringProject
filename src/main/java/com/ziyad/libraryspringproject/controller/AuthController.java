package com.ziyad.libraryspringproject.controller;

import com.ziyad.libraryspringproject.domain.dto.LoginRequest;
import com.ziyad.libraryspringproject.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){

    }
}
