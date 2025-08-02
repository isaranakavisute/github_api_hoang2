package com.example.super_springboot.mapper;

import com.example.super_springboot.dto.MrPolicyholderProjection;
import com.example.super_springboot.dto.request.ClaimRequest;
import com.example.super_springboot.dto.response.ClaimRequestFieldErrorDetail;
import com.example.super_springboot.entity.ClLine;
import com.example.super_springboot.repository.MR_MEMBER_Repository;
import com.example.super_springboot.repository.MR_POLICY_HOLDER_Repository;
import com.example.super_springboot.repository.MR_POLICY_PLAN_Repository;
import com.example.super_springboot.repository.PV_PROVIDER_Respository;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClLineMapper {
    private final MR_POLICY_HOLDER_Repository pohoRepository;
    private final MR_MEMBER_Repository memberRepository;
    private final MR_POLICY_PLAN_Repository poplRepository;
    private final PV_PROVIDER_Respository providerRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String DEFAULT_SCMA_OID_CL_PAY_TO = "CL_PAY_TO_D";
    private static final String DEFAULT_COUNTRY_TREATMENT = "COUNTRY_066";
    private static final String DEFAULT_CL_TYPE = "CL_TYPE_M";
    private static final String DEFAULT_STAGE_IDX = "PV";
    private static final String DEFAULT_LINE_STATUS = "CL_LINE_STATUS_IC";
    private static final String DEFAULT_YN = "YN_N";
    private static final String DEFAULT_CCY = "CCY_THB";
    private static final String DEFAULT_PAY_PRINT_LANG = "N";
    private static final String DEFAULT_HOSPITAL_NUMBER = "-";
    private static final String DEFAULT_HOSP_SUB_NO = "E-CLAIM";
    private static final String DEFAULT_CL_PAY_VOUCHER = "LB1";
    private static final String DEFAULT_DIAG_OID = "207867";
    private static final String DEFAULT_BEHD_OID = "20015";
    private static final BigDecimal DEFAULT_FX_RATE = new BigDecimal(1);
    private static final String DEFAULT_PEND_VAL = "Y";
    private static final String DEFAULT_USER = "API";

    public ClLine toEntity(ClaimRequest req, List<ClaimRequestFieldErrorDetail> errors) {
        ClLine clli = new ClLine();

        clli.setIncurDateFrom(parseDate(req.getStart(), "start", errors));
        clli.setIncurDateTo(parseDate(req.getFinish(), "finish", errors));
        clli.setRcvDate(LocalDate.now());
        clli.setScmaOidClPayTo(DEFAULT_SCMA_OID_CL_PAY_TO);
        BigDecimal totalBilled = parseBigDecimal(req.getTotal_billed(), "total_billed", errors);
        if (totalBilled != null) {
            clli.setPresAmt(totalBilled);
            clli.setOrgPresAmt(totalBilled);
            clli.setOrgAdjPresAmt(totalBilled);
            clli.setAdjPresAmt(totalBilled);
        }
        Long poplOid = poplRepository.getLatestPoplByPocyNo(req.getPolicy_no());

        clli.setBenPoplOid(poplOid);
        clli.setPoplOid(poplOid);
        String scmaOidProduct = poplRepository.getProductTypeByPoplOid(poplOid);
        clli.setScmaOidProduct(scmaOidProduct);
        clli.setProvName(req.getProvider());
        Long provOid = providerRepository.getProviderOidByName(req.getProvider());
        clli.setProvOid(provOid);
        clli.setScmaOidCountryTreatment(DEFAULT_COUNTRY_TREATMENT);
        clli.setScmaOidClType(DEFAULT_CL_TYPE);
        clli.setStageIdx(DEFAULT_STAGE_IDX);
        clli.setScmaOidClLineStatus(DEFAULT_LINE_STATUS);
        clli.setDiagOid(Long.parseLong(DEFAULT_DIAG_OID));
        clli.setBehdOid(Long.parseLong(DEFAULT_BEHD_OID));
        clli.setScmaOidYnEr(DEFAULT_YN);
        clli.setScmaOidYnTreatPlan(DEFAULT_YN);
        clli.setScmaOidYnWp(DEFAULT_YN);
        clli.setScmaOidCcyPres(DEFAULT_CCY);
        clli.setPayee(req.getMember_name());
        clli.setFxRate(DEFAULT_FX_RATE);
        clli.setScmaOidCcyPay(DEFAULT_CCY);
        clli.setPayFxRate(DEFAULT_FX_RATE);
        clli.setRemarks(req.getRemarks());
        clli.setPendVal(DEFAULT_PEND_VAL);
        clli.setPayPrintInOtherLangInd(DEFAULT_PAY_PRINT_LANG);
        clli.setHospitalNumber(DEFAULT_HOSPITAL_NUMBER);
        clli.setHospSubNo(DEFAULT_HOSP_SUB_NO);
        clli.setClPayVoucher(DEFAULT_CL_PAY_VOUCHER);
        clli.setScmaOidYnAcc(DEFAULT_YN);
        clli.setScmaOidYnHospitalPdn(DEFAULT_YN);
        Long membOid = memberRepository.getMembOidByNo(req.getMember_no());
        clli.setMembOid(membOid);

        List<MrPolicyholderProjection> pohoRs = (List<MrPolicyholderProjection>) pohoRepository.getPohoByMbrNo(req.getMember_no());
        MrPolicyholderProjection poho = pohoRs.get(0);
        if (poho != null) {
            clli.setPayAddr1(poho.getBillAddr1());
            clli.setPayAddr2(poho.getBillAddr2());
            clli.setPayAddr3(poho.getBillAddr3());
            clli.setPayAddr4(poho.getBillAddr4());
            clli.setScmaOidPayProvince(poho.getScmaOidBillProvince());
            clli.setScmaOidCountryPay(poho.getScmaOidCountryBillAddr());
            clli.setScmaOidClPaymentMethod(poho.getScmaOidClPayMethod());
            clli.setPayZipCde(poho.getBillZipCde());
        }

        clli.setCrtUser(DEFAULT_USER);
        clli.setUpdUser(DEFAULT_USER);
        clli.setCrtDate(LocalDateTime.now());
        clli.setUpdDate(LocalDateTime.now());

        return clli;
    }

    private LocalDate parseDate(String value, String field, List<ClaimRequestFieldErrorDetail> errors) {
        if (value == null || value.isBlank()) {
            errors.add(new ClaimRequestFieldErrorDetail(field, "Date value is missing", "E400"));
            return null;
        }

        try {
            return LocalDate.parse(value.trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            errors.add(new ClaimRequestFieldErrorDetail(field, "Invalid date format, expected yyyy-MM-dd", "E400"));
            return null;
        }
    }

    private BigDecimal parseBigDecimal(String value, String field, List<ClaimRequestFieldErrorDetail> errors) {
        if (value == null || value.isBlank()) {
            errors.add(new ClaimRequestFieldErrorDetail(field, "Value is missing", "E400"));
            return null;
        }

        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            errors.add(new ClaimRequestFieldErrorDetail(field, "Invalid number format", "E400"));
            return null;
        }
    }
}
