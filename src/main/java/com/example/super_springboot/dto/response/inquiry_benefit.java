package com.example.super_springboot.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class inquiry_benefit {
    private String member_no; 
    private String policy_no; 
    private String toc; 
    private String pplan; 
    private String BEN_HEAD; 
    private String coverage; 
    private String BenefitName; 
    private String BenefitUnit; 
    private String BenefitLimit; 
    private String AnnualLimit; 
    private String CoverageVal; 
    private String AnnualUnit; 



    public inquiry_benefit() {
        this.member_no = "";
        this.policy_no = "";
        this.toc = "";
        this.pplan = "";
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