package com.example._rent_apartment.application_exeption;

import com.example._rent_apartment.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RentApartmentExceptionHandler {

    @ExceptionHandler(NotFoundConfigIntegrationException.class)
    public ResponseEntity<?> notFoundConfigIntegrationProcessing(NotFoundConfigIntegrationException ex){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(100, ex.getExceptionDescription());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO.toString());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> nonValidToken(AuthException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
