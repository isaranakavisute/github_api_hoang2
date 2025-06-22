package com.example.super_springboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PV_PROVIDER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PvProvider {

    @Id
    @Column(name = "PROV_OID", nullable = false)
    private Long provOid;

    @Column(name = "PROV_CODE", length = 6, nullable = false, unique = true)
    private String provCode;

    @Column(name = "PROV_NAME")
    private String provName;

}
