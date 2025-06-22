package com.example.super_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.super_springboot.dto.MrPolicyholderProjection;
import com.example.super_springboot.entity.MrPolicyholder;

import java.util.Collection;

public interface MR_POLICY_HOLDER_Repository extends JpaRepository<MrPolicyholder, Integer> {

    @Query(value = "SELECT POHO_OID,POHO_NO,POHO_NAME_1,POHO_NAME_2,CUSTOMER_BRANCH,SCMA_OID_POHO_TYPE,BILL_ADDR_1,BILL_ADDR_2,BILL_ADDR_3,BILL_ADDR_4,SCMA_OID_COUNTRY_BILL_ADDR,SCMA_OID_BILL_PROVINCE,BILL_ZIP_CDE,SCMA_OID_CL_PAY_METHOD FROM MR_POLICYHOLDER where POHO_OID=:POHO_OID", nativeQuery = true)
    Collection<MrPolicyholder> get_poho_name(Long POHO_OID);

    @Query(value = """
        SELECT  ph.POHO_OID AS pohoOid,
                ph.POHO_NO AS pohoNo,
                ph.POHO_NAME_1 AS pohoName1,
                ph.POHO_NAME_2 AS pohoName2,
                ph.SCMA_OID_POHO_TYPE AS scmaOidPohoType,
                ph.BILL_ADDR_1 AS billAddr1,
                ph.BILL_ADDR_2 AS billAddr2,
                ph.BILL_ADDR_3 AS billAddr3,
                ph.BILL_ADDR_4 AS billAddr4,
                ph.SCMA_OID_COUNTRY_BILL_ADDR AS scmaOidCountryBillAddr,
                ph.SCMA_OID_BILL_PROVINCE AS scmaOidBillProvince,
                ph.BILL_ZIP_CDE AS billZipCde,
                ph.SCMA_OID_CL_PAY_METHOD AS scmaOidClPayMethod,
                ph.CUSTOMER_BRANCH AS customerBranch
        FROM mr_policyholder ph
        JOIN mr_member mbr on ph.poho_oid = mbr.poho_oid
        WHERE mbr.mbr_no = :mbrNo
        """, nativeQuery = true)
    Collection<MrPolicyholderProjection> getPohoByMbrNo(@Param("mbrNo") String mbrNo);
}
