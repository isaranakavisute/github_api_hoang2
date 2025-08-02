package com.example.super_springboot.service;

import com.example.super_springboot.dto.request.ClaimRequest;
import com.example.super_springboot.entity.ClClaim;
import com.example.super_springboot.entity.ClLine;
import com.example.super_springboot.helper.utils.DateUtils;
import com.example.super_springboot.repository.CL_CLAIM_Repository;
import com.example.super_springboot.repository.CL_LINE_Repository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimService {

    private final CL_CLAIM_Repository claimRepository;
    private final CL_LINE_Repository clLineRepository;

    public ClClaim proceedSavedClaim(ClClaim claim, ClLine clli, ClaimRequest request) throws Exception {
        // Save ClClaim
        claim.setClNo(generateNewClaimNo(clli.getRcvDate()));
        ClClaim savedClaim = claimRepository.save(claim);

        // Proceed to process the ClLines
        if (savedClaim != null) {
            Integer diffDays = Integer.valueOf(DateUtils.diffDay(clli.getIncurDateTo(), clli.getIncurDateFrom()));
            clli.setClamOid(savedClaim.getClamOid());
            clli.setLineNo(generateNewLineNo(claim.getClamOid()));
            clli.setLineRevNo(0);
            clli.setDays(diffDays);

            ClLine savedLine = clLineRepository.save(clli);
            if (savedLine != null) {
                return savedClaim;
            }
        }

        return null;
    }

    public String generateNewClaimNo(LocalDateTime rcvDate) {
        LocalDate dateOnly = rcvDate.toLocalDate();
        String prefix = dateOnly.format(DateTimeFormatter.ofPattern("yyMMdd"));
        String suffix = claimRepository.findNextClaimSuffix(prefix);
        return (suffix != null) ? prefix + suffix : prefix + "0001";
    }

    public String generateNewClaimNo(LocalDate rcvDate) {
        // Format LocalDate to yyMMdd
        String prefix = rcvDate.format(DateTimeFormatter.ofPattern("yyMMdd"));
        String suffix = claimRepository.findNextClaimSuffix(prefix);
        return (suffix != null) ? prefix + suffix : prefix + "0001";
    }

    public Integer generateNewLineNo(Long clamOid) {
        if (clamOid == null) {
            throw new IllegalArgumentException("clamOid must not be null");
        }

        return clLineRepository.findNextLineNo(clamOid);
    }
}
