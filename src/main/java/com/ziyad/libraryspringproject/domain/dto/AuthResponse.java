package com.ziyad.libraryspringproject.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private Long id;
    private String username;
    private String role;

}