package com.ziyad.libraryspringproject.controller;

import com.ziyad.libraryspringproject.domain.dto.LoginRequest;
import com.ziyad.libraryspringproject.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);

        String role = authentication.getAuthorities().iterator().next().getAuthority();
        String token = jwtService.generateToken(authentication.getName(),role);

        return token;
    }
}
