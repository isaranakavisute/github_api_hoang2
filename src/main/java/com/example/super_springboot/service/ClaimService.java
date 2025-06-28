package com.example.super_springboot.service;

import com.example.super_springboot.dto.MrPolicyholderProjection;
import com.example.super_springboot.dto.request.ClaimRequest;
import com.example.super_springboot.entity.ClClaim;
import com.example.super_springboot.entity.ClLine;
import com.example.super_springboot.helper.utils.DateUtils;
import com.example.super_springboot.repository.CL_CLAIM_Repository;
import com.example.super_springboot.repository.CL_LINE_Repository;
import com.example.super_springboot.repository.MR_MEMBER_Repository;
import com.example.super_springboot.repository.MR_POLICY_HOLDER_Repository;
import com.example.super_springboot.repository.MR_POLICY_PLAN_Repository;
import com.example.super_springboot.repository.PV_PROVIDER_Respository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimService {

    private final CL_CLAIM_Repository claimRepository;
    private final CL_LINE_Repository clLineRepository;
    private final MR_POLICY_HOLDER_Repository pohoRepository;
    private final PV_PROVIDER_Respository providerRepository;
    private final MR_MEMBER_Repository memberRepository;
    private final MR_POLICY_PLAN_Repository poplRepository;

    public ClClaim proceedSavedClaim(ClClaim claim, ClLine clli, ClaimRequest request) throws Exception {
        // Save ClClaim
        claim.setClNo(generateNewClaimNo(clli.getRcvDate()));
        ClClaim savedClaim = claimRepository.save(claim);

        // Proceed to process the ClLines
        if(savedClaim != null) {
            Integer diffDays = Integer.valueOf(DateUtils.diffDay(clli.getIncurDateTo(), clli.getIncurDateFrom()));
            Long provOid = providerRepository.getProviderOidByName(request.getProvider());
            Long membOid = clli.getMembOid() != null ? clli.getMembOid() : memberRepository.getMembOidByNo(request.getMember_no());
            List<MrPolicyholderProjection> pohoRs = (List<MrPolicyholderProjection>) pohoRepository.getPohoByMbrNo(request.getMember_no());
            MrPolicyholderProjection poho = pohoRs.get(0);

            String payAddr1 = clli.getPayAddr1() != null ? clli.getPayAddr1().toString() : poho.getBillAddr1();
            String payAddr2 = clli.getPayAddr2() != null ? clli.getPayAddr2().toString() : poho.getBillAddr2();
            String payAddr3 = clli.getPayAddr3() != null ? clli.getPayAddr3().toString() : poho.getBillAddr3();
            String payAddr4 = clli.getPayAddr4() != null ? clli.getPayAddr4().toString() : poho.getBillAddr4();
            String payProvince = clli.getScmaOidPayProvince() != null ? clli.getScmaOidPayProvince().toString() : poho.getScmaOidBillProvince();
            String countryPay = clli.getScmaOidCountryPay() != null ? clli.getScmaOidCountryPay().toString() : poho.getScmaOidCountryBillAddr();
            String paymentMethod = clli.getScmaOidClPaymentMethod() != null ? clli.getScmaOidClPaymentMethod().toString() : poho.getScmaOidClPayMethod();
            String payZipCde = clli.getPayZipCde() != null ? clli.getPayZipCde().toString() : poho.getBillZipCde();
            Long poplOid = clli.getPoplOid() != null ? clli.getPoplOid() : poplRepository.getLatestPoplByPocyNo(request.getPolicy_no());
            Long benPoplOid = clli.getBenPoplOid() != null ? clli.getBenPoplOid() : poplOid;
            Long diagOid = clli.getDiagOid() != null ? clli.getDiagOid() : 207867;
            Long behdOid = clli.getBehdOid() != null ? clli.getBehdOid() : 20015;

            clli.setClamOid(savedClaim.getClamOid());
            clli.setLineNo(generateNewLineNo(claim.getClamOid()));
            clli.setLineRevNo(0);
            clli.setDays(diffDays);
            clli.setProvOid(provOid);
            clli.setProvName(request.getProvider());
            clli.setMembOid(membOid);
            clli.setPoplOid(poplOid);
            clli.setBenPoplOid(benPoplOid);
            clli.setDiagOid(diagOid);
            clli.setBehdOid(behdOid);
            clli.setPayAddr1(payAddr1);
            clli.setPayAddr2(payAddr2);
            clli.setPayAddr3(payAddr3);
            clli.setPayAddr4(payAddr4);
            clli.setScmaOidCountryPay(countryPay);
            clli.setScmaOidClPaymentMethod(paymentMethod);
            clli.setScmaOidPayProvince(payProvince);
            clli.setPayZipCde(payZipCde);

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
