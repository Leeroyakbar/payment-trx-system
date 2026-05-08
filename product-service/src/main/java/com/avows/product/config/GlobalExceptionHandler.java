package com.avows.product.config;

import com.avows.product.dto.ApiResponseDTO;
import com.avows.product.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleNotFoundException(NotFoundException e) {
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .success(false)
                .errors(e.getMessage())
                .errorCodes("404")
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleRuntimeException(RuntimeException ex) {
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .success(false)
                .errors(ex.getMessage())
                .errorCodes("500")
                .build();
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleGeneralException(Exception ex) {
        ApiResponseDTO<Object> response = ApiResponseDTO.builder()
                .success(false)
                .errors("An unexpected error occurred")
                .errors(ex.getMessage())
                .errorCodes("999")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
