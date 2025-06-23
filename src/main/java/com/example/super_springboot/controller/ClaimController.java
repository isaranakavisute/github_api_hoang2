package com.example.super_springboot.controller;

import com.example.super_springboot.dto.request.ClaimRequest;
import com.example.super_springboot.dto.response.ApiResponse;
import com.example.super_springboot.dto.response.ClaimRequestFieldErrorDetail;
import com.example.super_springboot.entity.ClClaim;
import com.example.super_springboot.entity.ClLine;
import com.example.super_springboot.mapper.ClClaimMapper;
import com.example.super_springboot.mapper.ClLineMapper;
import com.example.super_springboot.service.ClaimService;
import com.example.super_springboot.service.ClaimValidationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;
    private final ClaimValidationService claimValidationService;

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
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, errors, "Mapping errors", "E400"));
        }

        // ------------- Create Claim ----------------
        try {
            ClClaim cl = claimService.proceedSavedClaim(claim, clLine, request);
            if (cl == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ApiResponse.builder()
                                .success(false)
                                .data(List.of())
                                .message("Claim could not be created")
                                .code("E403")
                                .build());
            }

            Map<String, Object> clMap = new HashMap<>();
            clMap.put("claim_no", cl.getClNo());
            List<Map<String, Object>> clResponse = List.of(clMap);
            
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .data(List.of(clResponse))
                    .message("Claim created successfully")
                    .code(200)
                    .build());

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ApiResponse.builder()
                    .success(false)
                    .data(List.of())
                    .message(ex.getMessage())
                    .code("E403")
                    .build());
        }
    }
}