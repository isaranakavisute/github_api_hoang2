package com.example.super_springboot.dto;

public interface MrPolicyholderProjection {

    Long getPohoOid();

    String getPohoNo();

    String getPohoName1();

    String getPohoName2();

    String getScmaOidPohoType();

    String getBillAddr1();

    String getBillAddr2();

    String getBillAddr3();

    String getBillAddr4();

    String getScmaOidCountryBillAddr();

    String getScmaOidBillProvince();

    String getBillZipCde();

    String getScmaOidClPayMethod();

    String getCustomerBranch();
}