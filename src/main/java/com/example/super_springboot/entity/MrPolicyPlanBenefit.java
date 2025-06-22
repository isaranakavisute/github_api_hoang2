package com.example.super_springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MR_POLICY_PLAN_BENEFIT")
public class MrPolicyPlanBenefit {
    @Id
    @Column(name = "POBE_OID", nullable = false)
    private Long id;

    @Column(name = "PLBE_OID", nullable = false)
    private Long PLBE_OID;

}