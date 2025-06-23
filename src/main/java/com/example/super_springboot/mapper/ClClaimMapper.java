package com.example.super_springboot.mapper;

import com.example.super_springboot.dto.request.ClaimRequest;
import com.example.super_springboot.dto.response.ClaimRequestFieldErrorDetail;
import com.example.super_springboot.entity.ClClaim;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ClClaimMapper {

    public static ClClaim toEntity(ClaimRequest req, List<ClaimRequestFieldErrorDetail> errors) {
        Set<String> VALID_CL_STATUSES = Set.of(
                "CL_STATUS_NC", "CL_STATUS_RC", "CL_STATUS_RA",
                "CL_STATUS_PV", "CL_STATUS_PC", "CL_STATUS_FC");

        ClClaim cl = new ClClaim();

        // ----------- rcv date -----------
        // LocalDateTime rcvDate = parseDateTime(req.getRcv_date(), "rcv_date", errors);
        // cl.setClNo(parseDateTime(req.getRcv_date()));
        cl.setScmaOidClStatus(req.getMember_no());

        // ----------- scma_oid_cl_status-----------
        String rawClStatus = req.getScma_oid_cl_status();
        String clStatus = (rawClStatus != null) ? rawClStatus.trim().toUpperCase() : "CL_STATUS_NC";
        if (VALID_CL_STATUSES.contains(clStatus)) {
            cl.setScmaOidClStatus(clStatus);
        } else {
            errors.add(new ClaimRequestFieldErrorDetail("scma_oid_cl_status", "Invalid value", "E400"));
        }

        // ----------- scmaOidYnForcePay -----------
        if (req.getScma_oid_yn_force_pay() != null) {
            String forcePay = req.getScma_oid_yn_force_pay().trim().toUpperCase();
            if ("YN_Y".equals(forcePay) || "YN_N".equals(forcePay)) {
                cl.setScmaOidYnForcePay(forcePay);
            } else {
                errors.add(new ClaimRequestFieldErrorDetail("scma_oid_yn_force_pay", "Invalid value", "E400"));
            }
        }

        cl.setRemark(req.getRemarks());

        String rawCrtUser = req.getCrt_user();
        String crtUser = (rawCrtUser != null) ? rawCrtUser.trim() : "API";
        cl.setCrtUser(crtUser);
        cl.setUpdUser(crtUser);
        cl.setCrtDate(LocalDateTime.now());
        cl.setUpdDate(LocalDateTime.now());

        return cl;
    }
}
