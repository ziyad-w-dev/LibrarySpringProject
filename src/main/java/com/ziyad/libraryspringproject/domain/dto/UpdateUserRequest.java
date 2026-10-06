package com.ziyad.libraryspringproject.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    @Email
    @Pattern(regexp = ".*\\S.*")
    private String email;

    @Pattern(regexp = ".*\\S.*")
    private String userName;

}
