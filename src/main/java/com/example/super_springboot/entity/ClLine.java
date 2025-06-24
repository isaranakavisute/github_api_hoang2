package com.example.super_springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "cl_line")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClLine {

    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    // @SequenceGenerator(name = "sequence", sequenceName = "HIBERNATE_SEQUENCE", allocationSize = 1)
    // @NotNull
    // @Column(name = "CLLI_OID", nullable = false, precision = 14, scale = 0)
    // private Long clliOid;

    // @Column(name = "CLAM_OID", nullable = false, precision = 14, scale = 0)
    // private Long clamOid;

    // @Column(name = "LINE_NO", nullable = false, precision = 10, scale = 0)
    // private Integer lineNo;

    // @Column(name = "LINE_REV_NO", nullable = false, precision = 2, scale = 0)
    // private Integer lineRevNo;

    // @Column(name = "REV_NO", precision = 2, scale = 0)
    // private Integer revNo;

    // @Column(name = "STAGE_IDX", length = 2)
    // private String stageIdx;

    // @Column(name = "RCV_DATE", nullable = false)
    // private LocalDate rcvDate;

    // @Column(name = "MEMB_OID", precision = 14, scale = 0)
    // private Long membOid;

    // @Column(name = "INCUR_DATE_FROM")
    // private LocalDate incurDateFrom;

    // @Column(name = "INCUR_DATE_TO")
    // private LocalDate incurDateTo;

    // @Column(name = "DAYS", precision = 14, scale = 0)
    // private Integer days;

    // @Column(name = "BEN_POPL_OID", precision = 14, scale = 0)
    // private Long benPoplOid;

    // @Column(name = "POPL_OID", precision = 14, scale = 0)
    // private Long poplOid;

    // @Column(name = "SCMA_OID_PRODUCT", length = 20, nullable = false)
    // private String scmaOidProduct;

    // @NotNull
    // @Column(name = "SCMA_OID_CL_TYPE", length = 20, nullable = false)
    // private String scmaOidClType;

    // @Column(name = "SCMA_OID_CL_PAY_TO", length = 20)
    // private String scmaOidClPayTo;

    // @Column(name = "PROV_OID", precision = 14, scale = 0)
    // private Long provOid;

    // @Column(name = "PROV_NAME", length = 200)
    // private String provName;

    // @Pattern(regexp = "^COUNTRY_\\d{3}$", message = "The format code must follow the COUNTRY_XXX format")
    // @Column(name = "SCMA_OID_COUNTRY_TREATMENT", length = 20)
    // private String scmaOidCountryTreatment;

    // @Column(name = "PAY_NAME1", length = 160)
    // private String payName1;

    // @Column(name = "PAY_NAME2", length = 160)
    // private String payName2;

    // @Column(name = "PAY_ADDR1", length = 200)
    // private String payAddr1;

    // @Column(name = "PAY_ADDR2", length = 200)
    // private String payAddr2;

    // @Column(name = "PAY_ADDR3", length = 200)
    // private String payAddr3;

    // @Column(name = "PAY_ADDR4", length = 200)
    // private String payAddr4;

    // @Column(name = "SCMA_OID_BED_TYPE")
    // private String scmaOidBedType;

    // @Column(name = "BEHD_OID", precision = 14, scale = 0)
    // private Long behdOid;

    // @Column(name = "DIAG_OID", precision = 14, scale = 0)
    // private Long diagOid;

    // @Column(name = "SYMPTOM_DATE")
    // private LocalDateTime symptomDate;

    // @Column(name = "SCMA_OID_YN_ER", length = 20)
    // private String scmaOidYnEr;

    // @Column(name = "SCMA_OID_YN_TREAT_PLAN", length = 20)
    // private String scmaOidYnTreatPlan;

    // @Column(name = "SCMA_OID_YN_WP", length = 20)
    // private String scmaOidYnWp;

    // @Pattern(regexp = "^CCY_[A-Z]{3}$", message = "The format code must follow the CCY_XXX format (Ex: CCY_THB)")
    // @Column(name = "SCMA_OID_CCY_PRES", length = 20)
    // private String scmaOidCcyPres;

    // @Column(name = "FX_RATE", precision = 13, scale = 4)
    // private BigDecimal fxRate;

    // @Column(name = "ORG_PRES_AMT", precision = 15, scale = 4)
    // private BigDecimal orgPresAmt;

    // @Column(name = "PRES_AMT", precision = 15, scale = 4)
    // private BigDecimal presAmt;

    // @Column(name = "ORG_ADJ_PRES_AMT", precision = 15, scale = 4)
    // private BigDecimal orgAdjPresAmt;

    // @Column(name = "ADJ_PRES_AMT", precision = 15, scale = 4)
    // private BigDecimal adjPresAmt;

    // @Column(name = "MAN_REJ_AMT", precision = 15, scale = 4)
    // private BigDecimal manRejAmt;

    // @Column(name = "OVR_DED_AMT", precision = 15, scale = 4)
    // private BigDecimal ovrDedAmt;

    // @Column(name = "OVR_APP_AMT", precision = 15, scale = 4)
    // private BigDecimal ovrAppAmt;

    // @Column(name = "APP_AMT", precision = 15, scale = 4)
    // private BigDecimal appAmt;

    // @Column(name = "BEN_SPEND", precision = 15, scale = 4)
    // private BigDecimal benSpend;

    // @Column(name = "PRIOR_PAID", precision = 15, scale = 4)
    // private BigDecimal priorPaid;

    // @Column(name = "SCMA_OID_CL_LINE_STATUS", length = 20)
    // private String scmaOidClLineStatus;

    // @Column(name = "SCMA_OID_CL_PAYMENT_METHOD", length = 20)
    // private String scmaOidClPaymentMethod;

    // @Column(name = "SCMA_OID_COUNTRY_PAY", length = 20)
    // private String scmaOidCountryPay;

    // @Column(name = "PAYEE", length = 80)
    // private String payee;

    // @Column(name = "PAY_DATE")
    // private LocalDate payDate;

    // @Column(name = "PAY_FX_RATE", precision = 13, scale = 4)
    // private BigDecimal payFxRate;

    // @Column(name = "PAY_AMT", precision = 15, scale = 4)
    // private BigDecimal payAmt;

    // @Column(name = "SCMA_OID_CCY_PAY", length = 20)
    // private String scmaOidCcyPay;

    // @Column(name = "REMARK", length = 4000)
    // private String remarks;

    // @Column(name = "PEND_VAL", length = 1)
    // private String pendVal;

    // @Column(name = "SCMA_OID_PAY_PROVINCE", length = 20)
    // private String scmaOidPayProvince;

    // @Column(name = "PAY_PRINT_IN_OTHER_LANG_IND")
    // private String payPrintInOtherLangInd;

    // @Column(name = "HOSPITAL_NUMBER", length = 100)
    // private String hospitalNumber;

    // @Column(name = "CL_PAY_VOUCHER", length = 15)
    // private String clPayVoucher;

    // @Column(name = "HOSP_SUB_NO", length = 20)
    // private String hospSubNo;

    // @Column(name = "PAY_ZIP_CDE", length = 10)
    // private String payZipCde;

    // @Column(name = "SCMA_OID_YN_ACC", length = 10)
    // private String scmaOidYnAcc;

    // @Column(name = "SCMA_OID_YN_HOSPITAL_PDN", length = 10)
    // private String scmaOidYnHospitalPdn;

    // @Column(name = "CRT_USER", length = 10)
    // private String crtUser;

    // @Column(name = "CRT_DATE")
    // private LocalDateTime crtDate;

    // @Column(name = "UPD_USER", length = 10)
    // private String updUser;

    // @Column(name = "UPD_DATE")
    // private LocalDateTime updDate;

    // @Column(name = "CHQ_DATE")
    // private LocalDate chqDate;

    // @Id
    // @Column(name = "CLLI_OID", nullable = false)
    // private Long id;

    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "HIBERNATE_SEQUENCE", allocationSize = 1)
    @NotNull
    @Column(name = "CLLI_OID", nullable = false, precision = 14, scale = 0)
    private Long clliOid;

    @NotNull
    @Column(name = "CLAM_OID", nullable = false)
    private Long clamOid;

    @Column(name = "INCUR_DATE_FROM")
    private LocalDate incurDateFrom;

    @Column(name = "INCUR_DATE_TO")
    private LocalDate incurDateTo;

    @NotNull
    @Column(name = "SCMA_OID_CL_TYPE", nullable = false)
    private String scmaOidClType;

    @Column(name = "SCMA_OID_BED_TYPE")
    private String scmaOidBedType;

    @Column(name = "PRES_AMT", precision = 15, scale = 4)
    private BigDecimal presAmt;

    @Column(name = "APP_AMT", precision = 15, scale = 4)
    private BigDecimal appAmt;

    @Column(name = "PRIOR_PAID", precision = 15, scale = 4)
    private BigDecimal priorPaid;

    @Column(name = "SCMA_OID_CL_LINE_STATUS")
    private String scmaOidClLineStatus;

    @Column(name = "PAY_DATE")
    private LocalDate payDate;

    @Column(name = "CHQ_DATE")
    private LocalDate chqDate;

    @Column(name = "PAY_AMT", precision = 15, scale = 4)
    private BigDecimal payAmt;

    @Column(name = "LINE_NO")
    private int lineNo;

    @Column(name = "PAYEE")
    private String payee;

    @Column(name = "MEMB_OID")
    private Long membOid;

    @Column(name = "RCV_DATE", nullable = false)
    private LocalDate rcvDate;

    @Column(name = "DAYS", precision = 14, scale = 0)
    private Integer days;

    @Column(name = "PAY_ADDR1", length = 200)
    private String payAddr1;

    @Column(name = "PAY_ADDR2", length = 200)
    private String payAddr2;

    @Column(name = "PAY_ADDR3", length = 200)
    private String payAddr3;

    @Column(name = "PAY_ADDR4", length = 200)
    private String payAddr4;

    @Column(name = "SCMA_OID_PAY_PROVINCE", length = 20)
    private String scmaOidPayProvince;

    @Column(name = "SCMA_OID_COUNTRY_PAY", length = 20)
    private String scmaOidCountryPay;

    @Column(name = "SCMA_OID_CL_PAYMENT_METHOD", length = 20)
    private String scmaOidClPaymentMethod;

    @Column(name = "PAY_ZIP_CDE", length = 10)
    private String payZipCde;

    @Column(name = "LINE_REV_NO", nullable = false, precision = 2, scale = 0)
    private Integer lineRevNo;

    @Column(name = "PROV_OID", precision = 14, scale = 0)
    private Long provOid;

    @Column(name = "PROV_NAME", length = 200)
    private String provName;

    @Column(name = "SCMA_OID_CL_PAY_TO", length = 20)
    private String scmaOidClPayTo;

    @Column(name = "ORG_PRES_AMT", precision = 15, scale = 4)
    private BigDecimal orgPresAmt;

    @Column(name = "ORG_ADJ_PRES_AMT", precision = 15, scale = 4)
    private BigDecimal orgAdjPresAmt;

    @Column(name = "ADJ_PRES_AMT", precision = 15, scale = 4)
    private BigDecimal adjPresAmt;

    @Column(name = "BEN_POPL_OID", precision = 14, scale = 0)
    private Long benPoplOid;

    @Column(name = "POPL_OID", precision = 14, scale = 0)
    private Long poplOid;
     
    @Pattern(regexp = "^COUNTRY_\\d{3}$", message = "The format code must follow the COUNTRY_XXX format")
    @Column(name = "SCMA_OID_COUNTRY_TREATMENT", length = 20)
    private String scmaOidCountryTreatment;

    @Column(name = "STAGE_IDX", length = 2)
    private String stageIdx;

    @Column(name = "BEHD_OID", precision = 14, scale = 0)
    private Long behdOid;

    @Column(name = "DIAG_OID", precision = 14, scale = 0)
    private Long diagOid;

    @Column(name = "SCMA_OID_YN_ER", length = 20)
    private String scmaOidYnEr;

    @Column(name = "SCMA_OID_YN_TREAT_PLAN", length = 20)
    private String scmaOidYnTreatPlan;

    @Column(name = "SCMA_OID_YN_WP", length = 20)
    private String scmaOidYnWp;

    @Pattern(regexp = "^CCY_[A-Z]{3}$", message = "The format code must follow the CCY_XXX format (Ex: CCY_THB)")
    @Column(name = "SCMA_OID_CCY_PRES", length = 20)
    private String scmaOidCcyPres;

    @Column(name = "FX_RATE", precision = 13, scale = 4)
    private BigDecimal fxRate;

    @Column(name = "SCMA_OID_CCY_PAY", length = 20)
    private String scmaOidCcyPay;

    @Column(name = "PAY_FX_RATE", precision = 13, scale = 4)
    private BigDecimal payFxRate;

    @Column(name = "REMARK", length = 4000)
    private String remarks;

    @Column(name = "PEND_VAL", length = 1)
    private String pendVal;

    @Column(name = "PAY_PRINT_IN_OTHER_LANG_IND")
    private String payPrintInOtherLangInd;

    @Column(name = "HOSPITAL_NUMBER", length = 100)
    private String hospitalNumber;

    @Column(name = "HOSP_SUB_NO", length = 20)
    private String hospSubNo;

    @Column(name = "CL_PAY_VOUCHER", length = 15)
    private String clPayVoucher;

    @Column(name = "SCMA_OID_YN_ACC", length = 10)
    private String scmaOidYnAcc;

    @Column(name = "SCMA_OID_YN_HOSPITAL_PDN", length = 10)
    private String scmaOidYnHospitalPdn;

    @Column(name = "CRT_USER", length = 10)
    private String crtUser;

    @Column(name = "CRT_DATE")
    private LocalDateTime crtDate;

    @Column(name = "UPD_USER", length = 10)
    private String updUser;

    @Column(name = "UPD_DATE")
    private LocalDateTime updDate;

    @Column(name = "SCMA_OID_PRODUCT", length = 20, nullable = false)
    private String scmaOidProduct;

    
   

    



     
    
    





     
         


      
           
}

