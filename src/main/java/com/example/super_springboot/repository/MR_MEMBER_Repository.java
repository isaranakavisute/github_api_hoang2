package com.example.super_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.super_springboot.entity.MrMember;

import java.util.Collection;

public interface MR_MEMBER_Repository extends JpaRepository<MrMember, Integer> {

    @Query(value = "SELECT MEMB_OID,POHO_OID,MBR_NO,MBR_FIRST_NAME,MBR_LAST_NAME,DOB,CL_PAY_ACCT_NO,EFF_DATE,SCMA_OID_MBR_TYPE,SCMA_OID_CIVIL_STATUS,SCMA_OID_SEX,ID_CARD_NO,CUSM_REF_NO,PLAN_NO,TIN FROM MR_MEMBER where ID_CARD_NO=:ID_CARD_NO", nativeQuery = true)
    Collection<MrMember> get_MEMB_OID(String ID_CARD_NO);

    @Query(value = """
        SELECT MEMB_OID,POHO_OID,MBR_NO,MBR_FIRST_NAME,MBR_LAST_NAME,DOB,CL_PAY_ACCT_NO,EFF_DATE,SCMA_OID_MBR_TYPE,SCMA_OID_CIVIL_STATUS,SCMA_OID_SEX,ID_CARD_NO,CUSM_REF_NO,PLAN_NO,TIN  
        FROM MR_MEMBER
        WHERE mbr_no = :mbrNo
        """, nativeQuery = true)
    Collection<MrMember> getMemberByMemberNo(String mbrNo);

    @Query(value = """
        SELECT memb_oid
        FROM mr_member
        WHERE mbr_no = :mbrNo
        """, nativeQuery = true)
    Long getMembOidByNo(@Param("mbrNo") String mbrNo);
}

