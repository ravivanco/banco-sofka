package com.banco.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SaldoNoDisponibleException.class)
    public ResponseEntity<?> manejarSaldoNoDisponible(SaldoNoDisponibleException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", ex.getMessage());
        error.put("fecha", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> manejarRuntime(RuntimeException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("mensaje", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
