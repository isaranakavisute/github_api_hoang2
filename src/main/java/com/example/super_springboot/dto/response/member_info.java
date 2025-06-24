package com.example.super_springboot.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class member_info {
    private Long memb_oid;
    private String member_no;
    private Long POPL_OID;
    private Long POCY_OID;
    private String policy_no;
    private String policy_holder;
    private String toc;
    private String company_name;
    private String empid;
    private String name;
    private String date_of_birth;
    private String account_no;
    private String sub_company_name;
    private String start_date;
    private String end_date;
    private String member_type;
    private String status;
    private String group_id;
    private String pplan;
    private String class_no;
    private String marital_status;
    private String ttype;
    private String gender;
    private String corporate_id;
    private String payor_code;
    private String branch;
    private String id_card;
    private String ref_policyno;
    private String policy_type;
    private String member_refno;
    private Long POBE_OID;
    private Long PLBE_OID;
    private Long BEHD_OID;
    private String BEN_HEAD;
    private String coverage;
    private String BenefitName;
    private String BenefitUnit;
    private String BenefitLimit;
    private String AnnualLimit;
    private String CoverageVal;
    private String AnnualUnit;



    public member_info() {
        this.memb_oid = (long) 0;
        this.member_no = "";
        this.POPL_OID = (long) 0;
        this.POCY_OID = (long) 0;
        this.policy_no = "";
        this.policy_holder = "";
        this.toc = "";
        this.company_name = "";
        this.empid = "";
        this.name = "";
        this.date_of_birth = "";
        this.account_no = "";
        this.sub_company_name = "";
        this.start_date = "";
        this.end_date = "";
        this.member_type = "";
        this.status = "";
        this.group_id = "";
        this.pplan = "";
        this.class_no = "";
        this.marital_status = "";
        this.ttype = "";
        this.gender = "";
        this.corporate_id = "";
        this.payor_code = "";
        this.branch = "";
        this.id_card = "";
        this.ref_policyno = "";
        this.policy_type = "";
        this.member_refno = "";
        this.POBE_OID = (long) 0;
        this.PLBE_OID = (long) 0;
        this.BEHD_OID = (long) 0;
        this.BEN_HEAD = "";
        this.coverage = "";
        this.BenefitName = "";
        this.BenefitUnit = "";
        this.BenefitLimit = "";;
        this.AnnualLimit = "";;
        this.CoverageVal = "";;
        this.AnnualUnit  = "";;
    }

}