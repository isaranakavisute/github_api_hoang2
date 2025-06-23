package com.example.super_springboot.mapper;

import com.example.super_springboot.dto.request.ClaimRequest;
import com.example.super_springboot.dto.response.ClaimRequestFieldErrorDetail;
import com.example.super_springboot.entity.ClLine;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;

public class ClLineMapper {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Set<String> YN_LS = Set.of("YN_N", "YN_Y");
    private static final Set<String> SCMA_OID_PRODUCTS = Set.of(
            "PRODUCT_TV_BV", "PRODUCT_CV", "PRODUCT_LF",
            "PRODUCT_LV", "PRODUCT_MD", "PRODUCT_PA");
    private static final Set<String> CL_LINE_STATUS_LS = Set.of(
            "CL_LINE_STATUS_PV", "CL_LINE_STATUS_PD", "CL_LINE_STATUS_SU", "CL_LINE_STATUS_AC",
            "CL_LINE_STATUS_RJ", "CL_LINE_STATUS_RV", "CL_LINE_STATUS_IC", "CL_LINE_STATUS_UE");
    private static final Set<String> CL_PAYMENT_METHOD_LS = Set.of(
            "CL_PAYMENT_METHOD_CQ", "CL_PAYMENT_METHOD_AT", "CL_PAYMENT_METHOD_TT", "CL_PAYMENT_METHOD_SP");

    public static ClLine toEntity(ClaimRequest req, List<ClaimRequestFieldErrorDetail> errors) {
        ClLine clli = new ClLine();

        clli.setIncurDateFrom(parseDate(req.getStart(), "start", errors));
        clli.setIncurDateTo(parseDate(req.getFinish(), "finish", errors));
        clli.setRcvDate(parseDate(req.getRcv_date(), "rcv_date", errors));

        // ------------ total_billed ----------------------
        BigDecimal totalBilled = parseBigDecimal(req.getTotal_billed(), "total_billed", errors);
        if (totalBilled != null) {
            clli.setPresAmt(totalBilled);
            clli.setOrgPresAmt(totalBilled);
            clli.setOrgAdjPresAmt(totalBilled);
            clli.setAdjPresAmt(totalBilled);
        }

        // ----------- ben_popl_oid -----------
        clli.setBenPoplOid(parseLong(req.getBen_popl_oid(), "ben_popl_oid", errors));
        clli.setPoplOid(parseLong(req.getPopl_oid(), "popl_oid", errors));

        // ---------------- scma_oid_product ----------------
        String rawScmaOidProduct = req.getScma_oid_product();
        String scmaOidProduct = (rawScmaOidProduct != null) ? rawScmaOidProduct.trim().toUpperCase() : "PRODUCT_MD";
        if (SCMA_OID_PRODUCTS.contains(scmaOidProduct)) {
            clli.setScmaOidProduct(scmaOidProduct);
        } else {
            errors.add(new ClaimRequestFieldErrorDetail("scma_oid_product", "Invalid value", "E400"));
        }

        // --------------------------------
        clli.setProvName(req.getProvider());

        // --------------------------------
        String rawCountryTreatment = req.getScma_oid_country_treatment();
        String countryTreatment = (rawCountryTreatment != null) ? rawCountryTreatment.trim().toUpperCase()
                : "COUNTRY_066";
        clli.setScmaOidCountryTreatment(countryTreatment);

        // -------------- scma_oid_cl_type ------------------
        String rawClType = req.getScma_oid_cl_type();
        String clType = (rawClType != null) ? rawClType.trim().toUpperCase() : "CL_TYPE_M";
        if ("CL_TYPE_M".equals(clType) || "CL_TYPE_P".equals(clType)) {
            clli.setScmaOidClType(req.getScma_oid_cl_type());
        } else {
            errors.add(new ClaimRequestFieldErrorDetail("scma_oid_cl_type", "Invalid value", "E400"));
        }

        // --------------------------------
        String rawStageIdx = req.getStage_idx();
        String stageIdx = (rawStageIdx != null) ? rawStageIdx.trim().toUpperCase() : "PV";
        clli.setStageIdx(stageIdx);

        // ---------------- scma_oid_cl_line_status ----------------
        String rawClLineStatus = req.getScma_oid_cl_line_status();
        String clLineStatus = (rawClLineStatus != null) ? rawClLineStatus.trim().toUpperCase() : "CL_LINE_STATUS_IC";
        if (CL_LINE_STATUS_LS.contains(clLineStatus)) {
            clli.setScmaOidClLineStatus(req.getScma_oid_cl_line_status());
        } else {
            errors.add(new ClaimRequestFieldErrorDetail("scma_oid_cl_line_status", "Invalid value", "E400"));
        }

        clli.setDiagOid(parseLong(req.getDiag_oid(), "diag_oid", errors));
        clli.setBehdOid(parseLong(req.getBehd_oid(), "behd_oid", errors));

        // ---------------- scma_oid_yn_er ----------------
        clli.setScmaOidYnEr(checkYnFormat(req.getScma_oid_yn_er(), "scma_oid_yn_er", errors));
        // ---------------- scma_oid_yn_treat_plan ----------------
        clli.setScmaOidYnTreatPlan(checkYnFormat(req.getScma_oid_yn_treat_plan(), "scma_oid_yn_wp", errors));
        // ---------------- scma_oid_yn_wp ----------------
        clli.setScmaOidYnWp(checkYnFormat(req.getScma_oid_yn_wp(), "scma_oid_yn_wp", errors));

        // ---------------- scma_oid_yn_wp ----------------
        String rawOidCcyPres = req.getScma_oid_ccy_pres();
        String oidCcyPres = (rawOidCcyPres != null) ? rawOidCcyPres.trim().toUpperCase() : "CCY_THB";
        clli.setScmaOidCcyPres(oidCcyPres);

        // -------------------------------
        clli.setPayee(req.getMember_name());
        clli.setFxRate(parseBigDecimal(req.getFx_rate(), "fx_rate", errors));

        // ---------------- scma_oid_yn_wp ----------------
        String rawOidCcyPay = req.getScma_oid_ccy_pay();
        String oidCcyPay = (rawOidCcyPay != null) ? rawOidCcyPay.trim().toUpperCase() : "CCY_THB";
        clli.setScmaOidCcyPay(oidCcyPay);

        // -------------------------------
        clli.setPayFxRate(parseBigDecimal(req.getPay_fx_rate(), "pay_fx_rate", errors));
        clli.setRemarks(req.getRemarks());
        clli.setPendVal("Y");

        // ---------------- pay_print_in_other_lang_ind ----------------
        String rawPayPrintInOtherLangInd = req.getPay_print_in_other_lang_ind();
        String payPrintInOtherLangInd = (rawPayPrintInOtherLangInd != null)
                ? rawPayPrintInOtherLangInd.trim().toUpperCase()
                : "N";
        if ("Y".equals(payPrintInOtherLangInd) || "N".equals(payPrintInOtherLangInd)) {
            clli.setPayPrintInOtherLangInd(payPrintInOtherLangInd);
        } else {
            errors.add(new ClaimRequestFieldErrorDetail("pay_print_in_other_lang_ind", "Invalid value", "E400"));
        }

        // ---------------- hospital_number ----------------
        String rawHospitalNumber = req.getHospital_number();
        String hospitalNumber = (rawHospitalNumber != null) ? rawHospitalNumber.trim().toUpperCase() : "-";
        clli.setHospitalNumber(hospitalNumber);

        // ---------------- hosp_sub_no ----------------
        String rawHospSubNo = req.getHosp_sub_no();
        String hospSubNo = (rawHospSubNo != null) ? rawHospSubNo.trim().toUpperCase() : "E-CLAIM";
        clli.setHospSubNo(hospSubNo);

        // ---------------- hosp_sub_no ----------------
        String rawClPayVoucher = req.getCl_pay_voucher();
        String clPayVoucher = (rawClPayVoucher != null) ? rawClPayVoucher.trim().toUpperCase() : "LB1";
        clli.setClPayVoucher(clPayVoucher);

        // ---------------------
        clli.setScmaOidYnAcc(checkYnFormat(req.getScma_oid_yn_acc(), "scma_oid_yn_acc", errors));
        clli.setScmaOidYnHospitalPdn(
                checkYnFormat(req.getScma_oid_yn_hospital_pdn(), "scma_oid_yn_hospital_pdn", errors));
        // clli.setPayZipCde(req.getPay_zip_cde());

        //---------------------
        String rawMembOid = req.getMemb_oid();
        if( rawMembOid != null) {
            clli.setMembOid(parseLong(rawMembOid, "memb_oid", errors));
        };
        
        //---------------------
        String rawPayAddr1 = req.getPay_addr1();
        if( rawPayAddr1 != null) {
            clli.setPayAddr1(rawPayAddr1.trim());
        };
        //---------------------
        String rawPayAddr2 = req.getPay_addr1();
        if( rawPayAddr2 != null) {
            clli.setPayAddr2(rawPayAddr2.trim());
        };
        //---------------------
        String rawPayAddr3 = req.getPay_addr1();
        if( rawPayAddr3 != null) {
            clli.setPayAddr1(rawPayAddr3.trim());
        };
        //---------------------
        String rawPayAddr4 = req.getPay_addr1();
        if( rawPayAddr4 != null) {
            clli.setPayAddr1(rawPayAddr4.trim());
        };
        //---------------------
        String rawPayProvince = req.getScma_oid_pay_province();
        if( rawPayProvince != null) {
            clli.setScmaOidPayProvince(rawPayProvince.trim());
        };
        //---------------------
        String rawCountryPay = req.getScma_oid_country_pay();
        if( rawCountryPay != null) {
            clli.setScmaOidCountryPay(rawCountryPay.trim());
        };
        //---------------------
        String rawPaymentMethod = req.getScma_oid_cl_payment_method();
        if( rawPaymentMethod != null) {
            clli.setScmaOidClPaymentMethod(rawPaymentMethod.trim());
        };

        //

        String rawCrtUser = req.getCrt_user();
        String crtUser = (rawCrtUser != null) ? rawCrtUser.trim() : "API";
        clli.setCrtUser(crtUser);
        clli.setUpdUser(crtUser);
        clli.setCrtDate(LocalDateTime.now());
        clli.setUpdDate(LocalDateTime.now());

        return clli;
    }

    private static LocalDate parseDate(String value, String field, List<ClaimRequestFieldErrorDetail> errors) {
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

    private static LocalDateTime parseDateTime(String value, String field, List<ClaimRequestFieldErrorDetail> errors) {
        if (value == null || value.isBlank()) {
            errors.add(new ClaimRequestFieldErrorDetail(field, "Datetime value is missing", "E400"));
            return null;
        }

        try {
            return LocalDateTime.parse(value.trim(), DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            errors.add(
                    new ClaimRequestFieldErrorDetail(field, "Invalid datetime format, expected yyyy-MM-dd'T'HH:mm:ss", "E400"));
            return null;
        }
    }

    private static Long parseLong(String value, String field, List<ClaimRequestFieldErrorDetail> errors) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            errors.add(new ClaimRequestFieldErrorDetail(field, "Invalid number", "E400"));
            return null;
        }
    }

    private static BigDecimal parseBigDecimal(String value, String field, List<ClaimRequestFieldErrorDetail> errors) {
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

    private static String checkYnFormat(String value, String field, List<ClaimRequestFieldErrorDetail> errors) {
        if (value == null || value.isBlank()) {
            return "YN_N"; // default to "YN_N"
        }

        String yn = value.trim().toUpperCase();
        if (YN_LS.contains(yn)) {
            return yn;
        }

        errors.add(new ClaimRequestFieldErrorDetail(field, "Invalid Y/N format (expected YN_Y or YN_N)", "E400"));
        return null;
    }
}
