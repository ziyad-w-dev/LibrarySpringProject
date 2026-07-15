package com.ziyad.libraryspringproject.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FullUpdateUserRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String userName;

}
