package com.ziyad.libraryspringproject.domain.dto;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartialUpdateBookRequest {

    private String name;

    private Integer pages;

    private Long authorId;

}
