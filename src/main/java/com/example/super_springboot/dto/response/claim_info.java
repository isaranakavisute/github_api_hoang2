package com.example.super_springboot.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class claim_info {
    long claim_id;
    private String claim_no;
    private String start;
    private String finish;
    private String claim_type;
    private String pay_to;
    private BigDecimal billed;
    private BigDecimal accepted;
    private BigDecimal unpaid;
    private String excess;
    private BigDecimal cash_member;
    private BigDecimal total_paid;
    private String status;
    private String coverage;
    private String payment_date;
    private String reason;
    private String status_th;
    private String status_code;
    private String benefit_description;




    public claim_info() {
        claim_id = (long) 0;
        claim_no="";
        start="";
        finish="";
        claim_type="";
        pay_to="";
        billed=BigDecimal.valueOf(0);
        accepted=BigDecimal.valueOf(0);
        unpaid=BigDecimal.valueOf(0);
        excess="";
        cash_member=BigDecimal.valueOf(0);
        total_paid=BigDecimal.valueOf(0);
        status="";
        coverage="";
        payment_date="";
        reason="";
        status_th="";
        status_code="";
        benefit_description="";

    }

}