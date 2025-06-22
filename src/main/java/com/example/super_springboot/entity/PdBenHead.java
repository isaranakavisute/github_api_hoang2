package com.example.super_springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PD_BEN_HEAD")
public class PdBenHead {
    @Id
    @Column(name = "BEHD_OID", nullable = false)
    private Long id;

    @Size(max = 5)
    @Column(name = "BEN_HEAD", length = 5)
    private String benHead;

    @Column(name = "SCMA_OID_BEN_TYPE")
    private String SCMA_OID_BEN_TYPE;
}