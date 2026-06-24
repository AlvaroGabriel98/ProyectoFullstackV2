package com.ms_stock.exception;

import com.ms_stock.dto.MessageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponseDTO handleNotFound(
            ResourceNotFoundException ex) {

        return MessageResponseDTO.builder()
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponseDTO handleValidation(
            MethodArgumentNotValidException ex) {

        return MessageResponseDTO.builder()
                .message("Error de validación")
                .build();
    }
}

