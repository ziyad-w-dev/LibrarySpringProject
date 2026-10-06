package com.ziyad.libraryspringproject.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;



}
