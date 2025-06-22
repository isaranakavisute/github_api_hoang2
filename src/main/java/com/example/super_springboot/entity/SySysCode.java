package com.example.super_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "PV_PROVIDER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SySysCode {

    @Id
    @Column(name = "SCMA_OID", nullable = false, length = 20)
    private String scmaOid;

    @Column(name = "SYS_TYPE", nullable = false, length = 20)
    private String sysType;

    @Column(name = "SYS_CODE", nullable = false, length = 20)
    private String sysCode;

    @Column(name = "SEQ")
    private Short seq;

    @Column(name = "FLAG", length = 10)
    private String flag;

    @Column(name = "CRT_USER", length = 10)
    private String crtUser;

    @Column(name = "CRT_DATE")
    private LocalDate crtDate;

    @Column(name = "UPD_USER", length = 10)
    private String updUser;

    @Column(name = "UPD_DATE")
    private LocalDate updDate;

}
