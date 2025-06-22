package com.example.super_springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PD_PLAN")
public class PdPlan {
    @Id
    @Column(name = "PLAN_OID", nullable = false)
    private Long id;

    @Size(max = 4)
    @NotNull
    @Column(name = "PLAN_ID", nullable = false, length = 4)
    private String planId;

    @Column(name = "PLAN_NO", nullable = false)
    private String plan_no;

    @Column(name = "PLAN_DESC", nullable = false)
    private String plan_desc;



}