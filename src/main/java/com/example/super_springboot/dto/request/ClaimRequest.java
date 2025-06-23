package com.example.super_springboot.dto.request;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ClaimRequest {

    @NotBlank(message = "member_no is required")
    //@Pattern(regexp = "\\d{9}", message = "member_no must be 9 digits")
    private String member_no;

    @NotBlank(message = "policy_no is required")
    //@Pattern(regexp = "\\d{14}", message = "policy_no must be 14 digits")
    private String policy_no;

    @NotBlank(message = "remarks is required")
    private String remarks;

    @NotBlank(message = "total_billed is required")
    private String total_billed;

    @NotNull(message = "start date is required")
    private String start;

    @NotNull(message = "finish date is required")
    private String finish;

    @NotNull(message = "member_name is required")
    private String member_name;

    @NotNull(message = "provider is required")
    private String provider;

    private String scma_oid_cl_status;

    @Pattern(regexp = "YN_[YN]", message = "Must be YN_Y or YN_N")
    private String scma_oid_yn_force_pay;

    private String stage_idx;
    private String rcv_date;
    private String scma_oid_product;
    private String scma_oid_cl_type;

    private String scma_oid_cl_pay_to;

    @Pattern(regexp = "COUNTRY_\\d+", message = "Invalid country format")
    private String scma_oid_country_treatment;

    @Pattern(regexp = "YN_[YN]")
    private String scma_oid_yn_er;

    @Pattern(regexp = "YN_[YN]")
    private String scma_oid_yn_treat_plan;

    @Pattern(regexp = "YN_[YN]")
    private String scma_oid_yn_wp;

    @Pattern(regexp = "CCY_[A-Z]{3}")
    private String scma_oid_ccy_pres;

    @DecimalMin("0")
    private String fx_rate;

    private String diag_oid;
    private String behd_oid;
    private String ben_popl_oid;
    private String popl_oid;
    private String scma_oid_cl_line_status;
    private String scma_oid_cl_payment_method;

    @Pattern(regexp = "CCY_[A-Z]{3}")
    private String scma_oid_ccy_pay;

    @DecimalMin("0")
    private String pay_fx_rate;
    private String scma_oid_yn_acc;

    private String scma_oid_yn_hospital_pdn;
    private String memb_oid;
    private String pay_addr1;
    private String pay_addr2;
    private String pay_addr3;
    private String pay_addr4;
    private String scma_oid_pay_province;
    private String scma_oid_country_pay;

    @Pattern(regexp = "[YN]")
    private String pay_print_in_other_lang_ind;
    private String hospital_number;
    private String hosp_sub_no;
    private String cl_pay_voucher;
    private String crt_user;
}

