package com.example.super_springboot.service;

import java.util.Collection;
import java.util.List;

import com.example.super_springboot.dto.request.ClaimRequest;
import com.example.super_springboot.dto.response.ClaimRequestFieldErrorDetail;
import com.example.super_springboot.entity.ClClaim;
import com.example.super_springboot.entity.ClLine;
import com.example.super_springboot.entity.MrMember;
import com.example.super_springboot.entity.MrPolicy;
import com.example.super_springboot.repository.MR_MEMBER_Repository;
import com.example.super_springboot.repository.MR_POLICY_Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimValidationService {

    private final MR_MEMBER_Repository memberRepository;
    private final MR_POLICY_Repository policyRepository;

    public List<ClaimRequestFieldErrorDetail> validate(ClClaim claim, ClLine clli, ClaimRequest request,
            List<ClaimRequestFieldErrorDetail> errors) {

        // check member_no 
        if (request.getMember_no() != null) {
            String memberNo = request.getMember_no().trim();
            Collection<MrMember> mbr = memberRepository.getMemberByMemberNo(memberNo);
            if (mbr.size() == 0) {
                errors.add(new ClaimRequestFieldErrorDetail("member_no", "Member No is not specified or invalid", "E401"));
            }
        }

        // check pocy_no 
        if (request.getPolicy_no() != null) {
            String pocyNo = request.getPolicy_no().trim();
            Collection<MrPolicy> pocy = policyRepository.getPolicyByPocyNo(pocyNo);
            if (pocy.size() == 0) {
                errors.add(new ClaimRequestFieldErrorDetail("policy_no", "Policy No is not specified or invalid", "E401"));
            }
        }

        // Validate logic start/finish 
        if (clli.getIncurDateFrom() != null && clli.getIncurDateTo() != null && clli.getIncurDateFrom().isAfter(clli.getIncurDateTo())) {
            errors.add(new ClaimRequestFieldErrorDetail("start/finish", "Start date must be before or equal to finish date", "E401"));
        }

        return errors;
    }
}
