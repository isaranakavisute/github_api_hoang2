package com.example.super_springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MR_MEMBER_PLAN")
public class MrMemberPlan {
    @Id
    @Column(name = "MEPL_OID", nullable = false)
    private Long mepl_Oid;

    @NotNull
    @Column(name = "MEMB_OID", nullable = false)
    private Long membOid;

    @NotNull
    @Column(name = "POPL_OID", nullable = false)
    private Long poplOid;

    @Column(name = "EFF_DATE", nullable = false)
    private String eff_date;

    @Column(name = "EXP_DATE", nullable = false)
    private String exp_date;
}