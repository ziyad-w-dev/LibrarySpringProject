package com.ziyad.libraryspringproject.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {


    private String email;

    private String userName;



}
