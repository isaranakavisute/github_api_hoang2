package com.example.super_springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "VW_PCHI_MOBILE_PERSONAL_INFO")
public class VwPchiMobilePersonalInfo {
    @Id
    @Size(max = 9)
    @NotNull
    @Column(name = "MEMBER_NUMBER", nullable = false, length = 9)
    private String memberNumber;

    @Size(max = 32)
    @Column(name = "POLICY_NUMBER", length = 32)
    private String policyNumber;

    @Size(max = 482)
    @Nationalized
    @Column(name = "POLICY_HOLDER", length = 482)
    private String policyHolder;

    @Size(max = 4)
    @NotNull
    @Column(name = "TOC", nullable = false, length = 4)
    private String toc;

    @Size(max = 482)
    @Nationalized
    @Column(name = "COMPANY_NAME", length = 482)
    private String companyName;

    @Size(max = 9)
    @NotNull
    @Column(name = "EMPID", nullable = false, length = 9)
    private String empid;

    @Size(max = 482)
    @Nationalized
    @Column(name = "NAME", length = 482)
    private String name;

    @NotNull
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

    @Size(max = 25)
    @Column(name = "ACCOUNT_NO", length = 25)
    private String accountNo;

    @Lob
    @Column(name = "SUB_COMPANY_NAME")
    private String subCompanyName;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Size(max = 200)
    @Nationalized
    @Column(name = "MEMBER_TYPE", length = 200)
    private String memberType;

    @Size(max = 8)
    @Column(name = "STATUS", length = 8)
    private String status;

    @Size(max = 14)
    @NotNull
    @Column(name = "GROUP_ID", nullable = false, length = 14)
    private String groupId;

    @Size(max = 10)
    @Column(name = "PPLAN", length = 10)
    private String pplan;

    @Lob
    @Column(name = "CLASS_NO")
    private String classNo;

    @Size(max = 20)
    @Column(name = "MARITAL_STATUS", length = 20)
    private String maritalStatus;

    @Lob
    @Column(name = "TTYPE")
    private String ttype;

    @Size(max = 20)
    @Column(name = "GENDER", length = 20)
    private String gender;

    @Lob
    @Column(name = "CORPORATE_ID")
    private String corporateId;

    @Lob
    @Column(name = "PAYOR_CODE")
    private String payorCode;

    @Size(max = 500)
    @Column(name = "BRANCH", length = 500)
    private String branch;

    @Size(max = 100)
    @Column(name = "ID_CARD", length = 100)
    private String idCard;

    @Size(max = 32)
    @Column(name = "REF_POLICYNO", length = 32)
    private String refPolicyno;

    @Size(max = 20)
    @Column(name = "POLICY_TYPE", length = 20)
    private String policyType;

    @NotNull
    @Column(name = "MEMBER_REFNO", nullable = false)
    private Long memberRefno;

    @Column(name = "UPD_DATE")
    private LocalDate updDate;

}