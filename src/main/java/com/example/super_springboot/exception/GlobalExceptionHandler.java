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
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(false)
                .data("Invalid input")
                .message("JSON parse error")
                .code("E400")
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    // Validation error (@Valid) not passed
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<ClaimRequestFieldErrorDetail>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ClaimRequestFieldErrorDetail> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> new ClaimRequestFieldErrorDetail(
                        e.getField(),
                        e.getDefaultMessage(),
                        e.getCode() != null ? e.getCode() : "E401"
                ))
                .collect(Collectors.toList());

        ApiResponse<List<ClaimRequestFieldErrorDetail>> response = ApiResponse.<List<ClaimRequestFieldErrorDetail>>builder()
                .success(false)
                .data(errors)
                .message("Validation failed")
                .code("E401")
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    // To make sure the app never crashes, add a global catch:
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleUnknownError(Exception ex) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(false)
                .data("Server error")
                .message("Unexpected error")
                .code("E500")
                .build();

        return ResponseEntity.status(500).body(response);
    }
}
