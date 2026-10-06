package com.ziyad.libraryspringproject.domain.dto;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartialUpdateBookRequest {

    @Pattern(regexp = ".*\\S.*")
    private String name;

    @Positive
    private Integer pages;

    @Positive
    private Long authorId;

}
