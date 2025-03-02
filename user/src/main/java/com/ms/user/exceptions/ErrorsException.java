package com.ms.user.exceptions;

import com.ms.user.dtos.ErrorDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> error404(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(ValidException.class)
    public ResponseEntity<ErrorDTO> error400(ValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

}
