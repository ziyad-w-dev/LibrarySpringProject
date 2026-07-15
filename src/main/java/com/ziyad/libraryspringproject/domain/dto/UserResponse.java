package com.ziyad.libraryspringproject.domain.dto;

import com.ziyad.libraryspringproject.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private Role role;


}
