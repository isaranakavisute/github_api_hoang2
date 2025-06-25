package com.example.super_springboot.entity;


import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MR_POLICY")
public class MrPolicy  {
    @Id
    @Column(name = "POCY_OID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "POHO_OID", nullable = false)
    private Long pohoOid;

    @Size(max = 14)
    @NotNull
    @Column(name = "POCY_NO", nullable = false, length = 14)
    private String pocyNo;

    @Column(name = "LMG_NO", nullable = false, length = 14)
    private String LMG_NO;

    @Column(name = "EXP_DATE", nullable = false)
    private LocalDate EXP_DATE;

   

}