package com.example.super_springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PD_PLAN_LIMIT")
public class PdPlanLimit {
    @Id
    @Column(name = "PLLI_OID", nullable = false)
    private Long id;

    @Size(max = 2)
    @NotNull
    @Column(name = "LIMIT_TYPE", nullable = false, length = 2)
    private String limitType;

    @Column(name = "VIS_YR")
    private Short visYr;

    @Column(name = "VIS_DAY")
    private Short visDay;

    @Column(name = "AMT_VIS", precision = 15, scale = 4)
    private BigDecimal amtVis;

    @Column(name = "AMT_YR", precision = 15, scale = 4)
    private BigDecimal amtYr;

    @Column(name = "AMT_DIS_YR", precision = 15, scale = 4)
    private BigDecimal amtDisYr;

    @Column(name = "AMT_LIFE", precision = 15, scale = 4)
    private BigDecimal amtLife;

    @Column(name = "DEDUCT_AMT", precision = 15, scale = 4)
    private BigDecimal deductAmt;

    @Column(name = "COPAY_PCT", precision = 7, scale = 4)
    private BigDecimal copayPct;

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

    @Column(name = "DAY_DIS_YR")
    private Short dayDisYr;

    @Column(name = "AMT_DAY", precision = 15, scale = 4)
    private BigDecimal amtDay;

    @Column(name = "AMT_SUR", precision = 15, scale = 4)
    private BigDecimal amtSur;

    @Column(name = "NSUR_YEAR")
    private Short nsurYear;

    @Column(name = "PREM_PCT", precision = 7, scale = 4)
    private BigDecimal premPct;

    @Column(name = "DEDUCT_AMT_DIS_YR", precision = 15, scale = 4)
    private BigDecimal deductAmtDisYr;

    @Column(name = "AMT_DIS_INCIDENT", precision = 15, scale = 4)
    private BigDecimal amtDisIncident;

    @Column(name = "AMT_TRIP", precision = 15, scale = 4)
    private BigDecimal amtTrip;

    @Column(name = "AMT_SET", precision = 15, scale = 4)
    private BigDecimal amtSet;

    @Column(name = "AMT_HOURS_UNIT", precision = 15, scale = 4)
    private BigDecimal amtHoursUnit;

    @Column(name = "AMT_MONTH", precision = 15, scale = 4)
    private BigDecimal amtMonth;

}