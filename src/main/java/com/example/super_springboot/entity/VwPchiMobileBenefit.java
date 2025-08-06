package com.example.super_springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "VW_PCHI_MOBILE_BENEFIT")
public class VwPchiMobileBenefit {
    @Id
    @Size(max = 18)
    @Column(name = "ID", length = 18)
    private String id;

    @Size(max = 4)
    @NotNull
    @Column(name = "TOC", nullable = false, length = 4)
    private String toc;

    @Size(max = 32)
    @Column(name = "POLICYNO", length = 32)
    private String policyno;

    @Size(max = 9)
    @NotNull
    @Column(name = "MEMBERNO", nullable = false, length = 9)
    private String memberno;

    @Size(max = 200)
    @Nationalized
    @Column(name = "COVERAGE", length = 200)
    private String coverage;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "PPLAN", nullable = false, length = 200)
    private String pplan;

    @Size(max = 5)
    @Column(name = "BENEFITID", length = 5)
    private String benefitid;

    @Size(max = 600)
    @Nationalized
    @Column(name = "BENEFITNAME", length = 600)
    private String benefitname;

    @Size(max = 30)
    @Column(name = "BENEFITUNIT", length = 30)
    private String benefitunit;

    @Column(name = "BENEFITLIMIT", precision = 15, scale = 4)
    private BigDecimal benefitlimit;

    @Column(name = "ANNUALLIMIT")
    private Long annuallimit;

    @Size(max = 20)
    @Column(name = "COVERAGEVAL", length = 20)
    private String coverageval;

    @Size(max = 3)
    @Column(name = "ANNUALUNIT", length = 3)
    private String annualunit;

}