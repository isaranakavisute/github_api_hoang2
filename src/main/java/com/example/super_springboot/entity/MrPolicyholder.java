package com.example.super_springboot.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MR_POLICYHOLDER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MrPolicyholder {
    @Id
    @Column(name = "POHO_OID", nullable = false, precision = 14, scale = 0)
    private Long poho_oid;

    @Column(name = "POHO_NO", length = 9, nullable = false)
    private String pohoNo;

    @Column(name = "POHO_NAME_1", length = 160, nullable = false)
    private String poho_name_1;

    @Column(name = "POHO_NAME_2", length = 160, nullable = true)
    private String poho_name_2;

    @Column(name = "CUSTOMER_BRANCH")
    private String customer_branch;

    @Column(name = "SCMA_OID_POHO_TYPE")
    private String SCMA_OID_POHO_TYPE;


    @Column(name = "BILL_ADDR_1")
    private String billAddr1;

    @Column(name = "BILL_ADDR_2")
    private String billAddr2;

    @Column(name = "BILL_ADDR_3")
    private String billAddr3;

    @Column(name = "BILL_ADDR_4")
    private String billAddr4;

    @Column(name = "SCMA_OID_BILL_PROVINCE")
    private String scmaOidBillProvince;

    @Column(name = "SCMA_OID_COUNTRY_BILL_ADDR")
    private String scmaOidCountryBillAddr;

    @Column(name = "BILL_ZIP_CDE")
    private String billZipCde;

    @Column(name = "SCMA_OID_CL_PAY_METHOD")
    private String scmaOidClPayMethod;
}
