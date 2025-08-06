package com.example.super_springboot.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.super_springboot.entity.VwPchiMobileClaim;

@Getter
@Setter
public class inquiry_claim_header1 {
    private String success; 
    private List<VwPchiMobileClaim> data; 
    private String message; 
    private String code; 
    
  
    public inquiry_claim_header1() {
       success = "true";
       data = new ArrayList<>();
       message = "successfully";
       code = "200";
    }

}