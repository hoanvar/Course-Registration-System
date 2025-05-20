package com.sr.serviceregistration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(error.getCode(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RegistrationNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleRegistrationNotFoundException(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Registration not found");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DuplicateRegistrationException.class)
    public ResponseEntity<Map<String,String>> handleDuplicateRegistrationException(DuplicateRegistrationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
