package com.ziyad.libraryspringproject.domain.dto;


import com.ziyad.libraryspringproject.domain.entity.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateBookResponse {


    private Long id;

    private String name;

    private int pages;

    private Author author;

}
