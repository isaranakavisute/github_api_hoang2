package com.example.super_springboot.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class inquiry_claim_detail {
    long claim_id; 
    private String claim_no; 
    private BigDecimal billed; 
    private BigDecimal accepted; 
    private BigDecimal unpaid; 
    private String excess; 
    private BigDecimal cash_member; 
    private String benefit_description; 




    public inquiry_claim_detail() {
        claim_id = (long) 0;
        claim_no="";
        billed=BigDecimal.valueOf(0);
        accepted=BigDecimal.valueOf(0);
        unpaid=BigDecimal.valueOf(0);
        excess="";
        cash_member=BigDecimal.valueOf(0);
        benefit_description="";

    }

}