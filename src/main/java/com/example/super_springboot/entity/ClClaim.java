package com.example.super_springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CL_CLAIM")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(
        name = "sequence",
        sequenceName = "HIBERNATE_SEQUENCE",
        allocationSize = 1
    )
    @Column(name = "CLAM_OID", nullable = false, precision = 14, scale = 0)
    private Long clamOid;

    @Size(max = 10)
    @NotNull
    @Column(name = "CL_NO", length = 10, nullable = false, unique = true)
    private String clNo;

    @NotNull
    @Column(name = "SCMA_OID_CL_STATUS", length = 20, nullable = false)
    private String scmaOidClStatus;

    @Column(name = "SCMA_OID_YN_FORCE_PAY", length = 20, nullable = false)
    private String scmaOidYnForcePay;

    @Size(max = 2000)
    @Column(name = "REMARK", length = 2000)
    private String remark;

    @Column(name = "CRT_USER", length = 10)
    private String crtUser;

    @Column(name = "CRT_DATE")
    private LocalDateTime crtDate;

    @Column(name = "UPD_USER", length = 10)
    private String updUser;

    @Column(name = "UPD_DATE")
    private LocalDateTime updDate;
}
