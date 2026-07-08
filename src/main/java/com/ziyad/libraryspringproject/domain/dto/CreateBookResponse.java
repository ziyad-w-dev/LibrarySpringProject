package com.ziyad.libraryspringproject.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookResponse {


    private Long id;

    private String name;

    private int pages;

    private String authorName;

}
