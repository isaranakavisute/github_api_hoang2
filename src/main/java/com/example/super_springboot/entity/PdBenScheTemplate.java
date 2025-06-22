package com.example.super_springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PD_BEN_SCHE_TEMPLATE")
public class PdBenScheTemplate {
    @Id
    @Column(name = "PDTP_OID", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "TEMP_NAME", length = 100)
    private String tempName;

    @Size(max = 100)
    @Column(name = "TITLE", length = 100)
    private String title;

    @Size(max = 150)
    @Column(name = "AREA_COVER", length = 150)
    private String areaCover;

    @Size(max = 8)
    @Column(name = "TEMP_ID", length = 8)
    private String tempId;

    @Size(max = 10)
    @Column(name = "CRT_USER", length = 10)
    private String crtUser;

    @Column(name = "CRT_DATE")
    private LocalDate crtDate;

    @Size(max = 10)
    @Column(name = "UPD_USER", length = 10)
    private String updUser;

    @Column(name = "UPD_DATE")
    private LocalDate updDate;

    @Size(max = 4)
    @Column(name = "PLAN_ID", length = 4)
    private String planId;

    @Size(max = 2)
    @Column(name = "REV_NO", length = 2)
    private String revNo;

    @Size(max = 100)
    @Column(name = "TEMP_HEADING", length = 100)
    private String tempHeading;

    @Column(name = "PRTY_OID")
    private Long prtyOid;

    @Size(max = 20)
    @Column(name = "SCMA_OID_CORE_OPT", length = 20)
    private String scmaOidCoreOpt;

    @Column(name = "EXPIRY_DATE")
    private LocalDate expiryDate;

    @Column(name = "EFFECTIVE_DATE")
    private LocalDate effectiveDate;

    @Size(max = 20)
    @Column(name = "SCMA_OID_YN_SUBTEMP", length = 20)
    private String scmaOidYnSubtemp;

}