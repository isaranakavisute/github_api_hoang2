package com.example.super_springboot.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.example.super_springboot.entity.VwPchiMobilePersonalInfo;

@Getter
@Setter
public class inquiry_personal_information1 {
    private String success; 
    private List<VwPchiMobilePersonalInfo> data; 
    private String message; 
    private String code; 
    
  
    public inquiry_personal_information1() {
       success = "true";
       data = new ArrayList<>();
       message = "successfully";
       code = "200";
    }

}