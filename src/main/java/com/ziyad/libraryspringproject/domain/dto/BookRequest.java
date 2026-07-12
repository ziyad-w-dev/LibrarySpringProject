package com.ziyad.libraryspringproject.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank
    private String name;

    @Positive
    private int pages;

    @NotNull
    private Long authorId;


}
