package com.example.super_springboot.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.super_springboot.entity.VwPchiMobileBenefit;

@Getter
@Setter
public class inquiry_benefit1 {
    private String success; 
    private List<VwPchiMobileBenefit> data; 
    private String message; 
    private String code; 
    
  
    public inquiry_benefit1() {
       success = "true";
       data = new ArrayList<>();
       message = "successfully";
       code = "200";
    }

}