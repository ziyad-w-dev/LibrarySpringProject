package com.ziyad.libraryspringproject.domain.dto;

import com.ziyad.libraryspringproject.domain.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotNull
    private Role role;


}
