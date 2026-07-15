package com.ziyad.libraryspringproject.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartialUpdateUserRequest {


    private String email;

    private String userName;



}
