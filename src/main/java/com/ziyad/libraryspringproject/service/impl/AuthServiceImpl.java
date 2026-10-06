package com.ziyad.libraryspringproject.service.impl;

import com.ziyad.libraryspringproject.domain.dto.AuthResponse;
import com.ziyad.libraryspringproject.domain.dto.LoginRequest;
import com.ziyad.libraryspringproject.security.CustomUserDetails;
import com.ziyad.libraryspringproject.security.JwtService;
import com.ziyad.libraryspringproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request){
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        String token = jwtService.generateToken(username, role);

        return AuthResponse.builder()
                .token(token)
                .id(userDetails.getId())
                .username(username)
                .role(role)
                .build();
    }
}
