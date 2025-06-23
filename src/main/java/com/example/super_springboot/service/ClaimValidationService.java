package com.example.super_springboot.service;

import java.util.Collection;
import java.util.List;

import com.example.super_springboot.dto.request.ClaimRequest;
import com.example.super_springboot.dto.response.ClaimRequestFieldErrorDetail;
import com.example.super_springboot.entity.ClClaim;
import com.example.super_springboot.entity.ClLine;
import com.example.super_springboot.entity.MrMember;
import com.example.super_springboot.entity.MrPolicy;
import com.example.super_springboot.entity.MrPolicyPlan;
import com.example.super_springboot.entity.PdBenHead;
import com.example.super_springboot.entity.PvProvider;
import com.example.super_springboot.entity.RtDiagnosis;
import com.example.super_springboot.entity.SySysCode;
import com.example.super_springboot.repository.MR_MEMBER_Repository;
import com.example.super_springboot.repository.MR_POLICY_PLAN_Repository;
import com.example.super_springboot.repository.MR_POLICY_Repository;
import com.example.super_springboot.repository.PD_BEN_HEAD_Repository;
import com.example.super_springboot.repository.PV_PROVIDER_Respository;
import com.example.super_springboot.repository.RT_DIAGNOSIS_Repository;
import com.example.super_springboot.repository.SY_SYS_CODE_Respository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimValidationService {

    private final MR_MEMBER_Repository memberRepository;
    private final MR_POLICY_Repository policyRepository;
    private final MR_POLICY_PLAN_Repository policyPlanRepository;
    private final PV_PROVIDER_Respository providerRepository;
    private final SY_SYS_CODE_Respository sySysCodeRepository;
    private final RT_DIAGNOSIS_Repository rtDiagnosisRepository;
    private final PD_BEN_HEAD_Repository benheadRepository;

    public List<ClaimRequestFieldErrorDetail> validate(ClClaim claim, ClLine clli, ClaimRequest request,
            List<ClaimRequestFieldErrorDetail> errors) {

        // check member_no ------------------------
        if (request.getMember_no() != null) {
            String memberNo = request.getMember_no().trim();
            Collection<MrMember> mbr = memberRepository.getMemberByMemberNo(memberNo);
            if (mbr.size() == 0) {
                errors.add(new ClaimRequestFieldErrorDetail("member_no", "Member No is not specified or invalid", "E401"));
            }
        }

        // check pocy_no ------------------------
        if (request.getPolicy_no() != null) {
            String pocyNo = request.getPolicy_no().trim();
            Collection<MrPolicy> pocy = policyRepository.getPolicyByPocyNo(pocyNo);
            if (pocy.size() == 0) {
                errors.add(new ClaimRequestFieldErrorDetail("policy_no", "Policy No is not specified or invalid", "E401"));
            }
        }

        // popl ------------------------
        if (request.getPopl_oid() != null) {
            String poplOid = request.getPopl_oid().trim();
            Collection<MrPolicyPlan> pocyPlan = policyPlanRepository.getPolicyPlanByPoplOid(poplOid);
            if (pocyPlan.size() == 0) {
                errors.add(new ClaimRequestFieldErrorDetail("popl_oid", "Popl Id is not specified or invalid", "E401"));
            }
        }
        if (request.getBen_popl_oid() != null) {
            String benPoplOid = request.getBen_popl_oid().trim();
            Collection<MrPolicyPlan> benPocyPlan = policyPlanRepository.getPolicyPlanByPoplOid(benPoplOid);
            if (benPocyPlan.size() == 0) {
                errors.add(new ClaimRequestFieldErrorDetail("ben_popl_oid", "Ben Popl Id is not specified or invalid", "E401"));
            }
        }

        // provider ------------------------
        if (request.getProvider() != null) {
            String provName = request.getProvider().trim();
            Collection<PvProvider> prov = providerRepository.getProvByProvName(provName);
            if (prov.size() == 0) {
                errors.add(new ClaimRequestFieldErrorDetail("provider", "Provider is not specified or invalid", "E401"));
            }
        }

        // ben type ------------------------
        if (request.getBehd_oid() != null) {
            String BEHD_OID = request.getBehd_oid().trim();
            Collection<PdBenHead> benHead = benheadRepository.get_BEN_HEAD(Long.parseLong(BEHD_OID));
            if (benHead.size() == 0) {
                errors.add(new ClaimRequestFieldErrorDetail("behd_oid", "Benefit Head is not specified or invalid", "E401"));
            }
        }

        // diagCode ------------------------
        if (request.getDiag_oid() != null) {
            String diagOid = request.getDiag_oid().trim();
            Collection<RtDiagnosis> diag = rtDiagnosisRepository.getRtDiagnosisByOid(diagOid);
            if (diag.size() == 0) {
                errors.add(new ClaimRequestFieldErrorDetail("diag_oid", "Diagnosis Code is not specified or invalid", "E401"));
            }
        }

        // diagCode ------------------------
        if (request.getScma_oid_country_treatment() != null) {
            String country = request.getScma_oid_country_treatment().trim();
            Collection<SySysCode> countryRs = sySysCodeRepository.getSySysCodeBySmaOid(country);
            if (countryRs.size() == 0) {
                errors.add(new ClaimRequestFieldErrorDetail("scma_oid_country_treatment",
                        "Treatment Country is not specified or invalid", "E401"));
            }
        }

        // presCcy ------------------------
        if (request.getScma_oid_ccy_pres() != null) {
            String ccyPres = request.getScma_oid_ccy_pres().trim();
            Collection<SySysCode> ccyPresRs = sySysCodeRepository.getSySysCodeBySmaOid(ccyPres);
            if (ccyPresRs.size() == 0) {
                errors.add(new ClaimRequestFieldErrorDetail("scma_oid_ccy_pres",
                        "Presented Currency is not specified or invalid", "E401"));
            }
        }

        // payCcy ------------------------
        if (request.getScma_oid_ccy_pay() != null) {
            String ccyPay = request.getScma_oid_ccy_pay().trim();
            Collection<SySysCode> ccyPayRs = sySysCodeRepository.getSySysCodeBySmaOid(ccyPay);
            if (ccyPayRs.size() == 0) {
                errors.add(new ClaimRequestFieldErrorDetail("scma_oid_ccy_pay",
                        "Payment Currency is not specified or invalid", "E401"));
            }
        }

        return errors;
    }
}
