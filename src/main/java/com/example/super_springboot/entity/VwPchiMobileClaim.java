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
import java.time.LocalDate;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "VW_PCHI_MOBILE_CLAIM")
public class VwPchiMobileClaim {
    @Id
    @Column(name = "ID")
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "CLAIM_NO", nullable = false, length = 10)
    private String claimNo;

    @Column(name = "INCUR_FROM")
    private LocalDate incurFrom;

    @Column(name = "INCUR_TO")
    private LocalDate incurTo;

    @Size(max = 20)
    @Column(name = "CLAIM_TYPE", length = 20)
    private String claimType;

    @Size(max = 80)
    @Nationalized
    @Column(name = "PAY_TO", length = 80)
    private String payTo;

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

    @Column(name = "TOTAL_PAID")
    private Long totalPaid;

    @Size(max = 22)
    @Column(name = "STATUS", length = 22)
    private String status;

    // @Size(max = 200)
    // @Nationalized
    // @Column(name = "COVERAGE", length = 200)
    // private String coverage;

    @Column(name = "PAYMENT_DATE")
    private LocalDate paymentDate;

    @Size(max = 2000)
    @Nationalized
    @Column(name = "REASON", length = 2000)
    private String reason;

    @Size(max = 48)
    @Column(name = "STATUS_TH", length = 48)
    private String statusTh;

    @Size(max = 20)
    @Column(name = "STATUS_CODE", length = 20)
    private String statusCode;

    @Size(max = 9)
    @NotNull
    @Column(name = "MEMBERNO", nullable = false, length = 9)
    private String memberno;

}