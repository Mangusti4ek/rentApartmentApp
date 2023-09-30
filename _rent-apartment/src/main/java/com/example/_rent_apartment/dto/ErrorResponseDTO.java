package com.example._rent_apartment.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ErrorResponseDTO {
    private Integer codeException;

    private String description;

}
