package com.example.super_springboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClaimRequestFieldErrorDetail {
    private String field;
    private String message;
}