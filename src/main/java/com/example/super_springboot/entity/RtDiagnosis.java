package com.example.super_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RT_DIAGNOSIS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RtDiagnosis {

    @Id
    @Column(name = "DIAG_OID", nullable = false)
    private Long diagOid;

    @Column(name = "DIAG_CODE", nullable = false)
    private String diagCode;

    @Column(name = "DIAG_DESC")
    private String diagDesc;

}