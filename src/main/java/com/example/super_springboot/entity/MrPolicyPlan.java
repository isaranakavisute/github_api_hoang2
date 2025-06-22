package com.example.super_springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MR_POLICY_PLAN")
public class MrPolicyPlan {
    @Id
    @Column(name = "POPL_OID", nullable = false)
    private Long poplOid;

    @NotNull
    @Column(name = "POCY_OID", nullable = false)
    private Long pocyOid;

    @NotNull
    @Column(name = "PLAN_OID", nullable = false)
    private Long planOid;

}