package com.ziyad.libraryspringproject.controller;

import com.ziyad.libraryspringproject.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;


    public String login(){

    }
}
