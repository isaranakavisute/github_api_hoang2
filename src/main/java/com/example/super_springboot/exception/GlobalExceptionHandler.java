package com.example.super_springboot.exception;

import com.example.super_springboot.dto.response.ApiResponse;
import com.example.super_springboot.dto.response.ClaimRequestFieldErrorDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.converter.HttpMessageNotReadableException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // JSON format error cannot be parsed
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<String>> handleParseError(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(
            new ApiResponse<>(false, List.of("Invalid input"), "JSON parse error", "E400")
        );
    }

    // Validation error (@Valid) not passed
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ClaimRequestFieldErrorDetail>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ClaimRequestFieldErrorDetail> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(e -> new ClaimRequestFieldErrorDetail(e.getField(), e.getDefaultMessage()))
            .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(
            new ApiResponse<>(false, errors, "Validation failed", "E400")
        );
    }

    // To make sure the app never crashes, add a global catch:
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleUnknownError(Exception ex) {
        return ResponseEntity.status(500).body(
            new ApiResponse<>(false, List.of("Server error"), "Unexpected error", "E500")
        );
    }
}
