package com.ziyad.libraryspringproject.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

}
