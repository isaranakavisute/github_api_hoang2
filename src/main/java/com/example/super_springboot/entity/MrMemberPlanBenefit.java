package com.example.super_springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MR_MEMBER_PLAN_BENEFIT")
public class MrMemberPlanBenefit {
    @Id
    @Column(name = "MEBE_OID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "MEPL_OID", nullable = false)
    private Long meplOid;

    @Column(name = "POBE_OID", nullable = false)
    private Long pobeOid;

}