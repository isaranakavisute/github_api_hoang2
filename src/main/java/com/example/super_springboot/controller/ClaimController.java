package com.example.super_springboot.controller;

import com.example.super_springboot.dto.request.ClaimRequest;
import com.example.super_springboot.dto.response.ApiResponse;
import com.example.super_springboot.dto.response.ClaimRequestFieldErrorDetail;
import com.example.super_springboot.entity.ClClaim;
import com.example.super_springboot.entity.ClLine;
import com.example.super_springboot.mapper.ClClaimMapper;
import com.example.super_springboot.mapper.ClLineMapper;
import com.example.super_springboot.service.ApiAuditLogService;
import com.example.super_springboot.service.ClaimService;
import com.example.super_springboot.service.ClaimValidationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;
    private final ClaimValidationService claimValidationService;
    private final ApiAuditLogService apiAuditLogService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createClaim(@Valid @RequestBody ClaimRequest request) {

        List<ClaimRequestFieldErrorDetail> errors = new ArrayList<>();
        ClClaim claim = ClClaimMapper.toEntity(request, errors);
        ClLine clLine = ClLineMapper.toEntity(request, errors);

        // ------------ Validate logic ------------
        if (clLine.getIncurDateFrom() != null && clLine.getIncurDateTo() != null
                && clLine.getIncurDateFrom().isAfter(clLine.getIncurDateTo())) {
            errors.add(new ClaimRequestFieldErrorDetail("start/finish",
                    "Start date must be before or equal to finish date", "E401"));
        }

        if (clLine.getRcvDate() != null && clLine.getRcvDate().isAfter(LocalDate.now())) {
            errors.add(new ClaimRequestFieldErrorDetail("rcv_date", "Receive date cannot be in the future", "E401"));
        }

        // ------------ Check existence in the database ------------
        claimValidationService.validate(claim, clLine, request, errors);

        if (!errors.isEmpty()) {
            ApiResponse<List<ClaimRequestFieldErrorDetail>> response = ApiResponse.<List<ClaimRequestFieldErrorDetail>>builder()
                .success(false)
                .data(errors)
                .message("Mapping errors")
                .code("E400")
                .build();

            apiAuditLogService.logApiCall("POST", "/api/claims", request, response, "FAILURE");
            return ResponseEntity.badRequest().body(response);     
        }

        // ------------- Create Claim ----------------
        try {
            ClClaim cl = claimService.proceedSavedClaim(claim, clLine, request);
            if (cl == null) {
                ApiResponse<?> response = ApiResponse.builder()
                    .success(false)
                    .data(null)
                    .message("Claim could not be created")
                    .code("E403")
                    .build();

                apiAuditLogService.logApiCall("POST", "/api/claims", request, response, "FAILURE");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);           
            }

            Map<String, Object> clMap = Map.of("claim_no", cl.getClNo());
            ApiResponse<Map<String, Object>> response = ApiResponse.<Map<String, Object>>builder()
                .success(true)
                .data(clMap)
                .message("Claim created successfully")
                .code(200)
                .build();

            apiAuditLogService.logApiCall("POST", "/api/claims", request, response, "SUCCESS");
            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .data(null)
                .message(ex.getMessage())
                .code("E403")
                .build();

            apiAuditLogService.logApiCall("POST", "/api/claims", request, response, "FAILURE");
            return ResponseEntity.badRequest().body(response);
        }
    }
}