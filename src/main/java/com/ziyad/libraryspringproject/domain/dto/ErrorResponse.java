package com.ziyad.libraryspringproject.domain.dto;

import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {


    private String message;

    private int status;

    private LocalDateTime timestamp;


}
