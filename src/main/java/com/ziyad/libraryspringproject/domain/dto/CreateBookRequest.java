package com.ziyad.libraryspringproject.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateBookRequest {

    @NotBlank
    private String name;

    @Positive
    private int pages;

    @NotNull
    private Long authorId;


}
