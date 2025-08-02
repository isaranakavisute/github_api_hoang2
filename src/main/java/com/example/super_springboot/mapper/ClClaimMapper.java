package com.example.super_springboot.mapper;

import com.example.super_springboot.dto.request.ClaimRequest;
import com.example.super_springboot.dto.response.ClaimRequestFieldErrorDetail;
import com.example.super_springboot.entity.ClClaim;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClClaimMapper {
    private static final String DEFAULT_SCMA_OID_CL_STATUS = "CL_STATUS_NC";
    private static final String DEFAULT_SCMA_OID_FORCE_PAY = "YN_N";
    private static final String DEFAULT_USER = "API";

    public ClClaim toEntity(ClaimRequest req, List<ClaimRequestFieldErrorDetail> errors) {
        ClClaim cl = new ClClaim();
        cl.setScmaOidClStatus(DEFAULT_SCMA_OID_CL_STATUS);
        cl.setScmaOidYnForcePay(DEFAULT_SCMA_OID_FORCE_PAY);
        cl.setCrtUser(DEFAULT_USER);
        cl.setUpdUser(DEFAULT_USER);
        cl.setCrtDate(LocalDateTime.now());
        cl.setUpdDate(LocalDateTime.now());
        return cl;
    }
}
