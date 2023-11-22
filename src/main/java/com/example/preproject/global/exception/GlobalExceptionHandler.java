package com.example.preproject.global.exception;

import com.example.preproject.global.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorMessage> badRequestException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorityViolationException.class)
    public ResponseEntity<ErrorMessage> authorityException(AuthorityViolationException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> nullPointerException(NullPointerException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ApiResponse<?>> handleException(CustomException ex) {
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
                .body(ApiResponse.error(ex.getErrorCode().getMessage()));
    }
}