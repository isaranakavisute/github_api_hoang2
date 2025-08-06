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
@Table(name = "VW_PCHI_MOBILE_CLAIM_DETAIL")
public class VwPchiMobileClaimDetail {
    @Id
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "CLAIM_ID", nullable = false)
    private Long claimId;

    @Size(max = 10)
    @NotNull
    @Column(name = "CLAIM_NO", nullable = false, length = 10)
    private String claimNo;

    @Size(max = 200)
    @Nationalized
    @Column(name = "BENEFIT_DESCRIPTION", length = 200)
    private String benefitDescription;

    @Column(name = "BILLED")
    private Long billed;

    @Column(name = "ACCEPTED")
    private Long accepted;

    @Column(name = "UNPAID")
    private Long unpaid;

    @Column(name = "EXCESS")
    private Long excess;

    @Column(name = "CASH_MEMBER", precision = 15, scale = 4)
    private BigDecimal cashMember;

    @Size(max = 9)
    @NotNull
    @Column(name = "MEMBERNO", nullable = false, length = 9)
    private String memberno;

}