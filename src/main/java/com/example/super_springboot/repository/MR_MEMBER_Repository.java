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

    @Query(value = "SELECT MEMB_OID,POHO_OID,MBR_NO,MBR_FIRST_NAME,MBR_LAST_NAME,DOB,CL_PAY_ACCT_NO,EFF_DATE,SCMA_OID_MBR_TYPE,SCMA_OID_CIVIL_STATUS,SCMA_OID_SEX,ID_CARD_NO,CUSM_REF_NO,PLAN_NO,TIN FROM MR_MEMBER where ROWNUM <= 1000 ORDER BY MEMB_OID ASC ", nativeQuery = true)
    Collection<MrMember> get_1000_records();

    @Query(value = "SELECT MEMB_OID,POHO_OID,MBR_NO,MBR_FIRST_NAME,MBR_LAST_NAME,DOB,CL_PAY_ACCT_NO,EFF_DATE,SCMA_OID_MBR_TYPE,SCMA_OID_CIVIL_STATUS,SCMA_OID_SEX,ID_CARD_NO,CUSM_REF_NO,PLAN_NO,TIN FROM MR_MEMBER where MBR_NO=:MBR_NO", nativeQuery = true)
    Collection<MrMember> get_MEMB_OID_From_MBR_NO(String MBR_NO);

    @Query(value = """
    select MEMB.MBR_NO
    from MR_MEMBER MEMB
    inner join 
    ( select distinct MEPL.MEMB_OID
        , case SUBSTR(POCY.LMG_NO, 7, 1)
            when 'P' then 'PA'
            when 'T' then 'Travel'
            else
              case
                when PRTY.SCMA_OID_PROD_TYPE in ('PRODUCT_TYPE_LPT', 'PRODUCT_TYPE_GPA') then 'PA'
                when PLAN.SCMA_OID_REF_PROD_TYPE in ('REF_PRODUCT_TYPE_PAT') then 'PA'
                else 'Health'
              end
          end as "PRODUCT"
        , case 
            when PLAN.SCMA_OID_REF_PROD_TYPE = 'REF_PRODUCT_TYPE_PA' and POPL.POCY_PLAN_DESC like '%STUDENT%' then 'Group Student'
            when PLAN.SCMA_OID_REF_PROD_TYPE = 'REF_PRODUCT_TYPE_SSB' then 'Group SSB'
            when PRTY.SCMA_OID_PROD_TYPE = 'PRODUCT_TYPE_LPT' then 'Loan Protection'
            when POHO.SCMA_OID_POHO_TYPE = 'POHO_TYPE_G' then 'Group'
            when PLAN.SCMA_OID_REF_PROD_TYPE in ('REF_PRODUCT_TYPE_COV' -- COVID
              , 'REF_PRODUCT_TYPE_CAC' -- Cancer Care
              , 'REF_PRODUCT_TYPE_CAT' -- Cancer Care Tele
              , 'REF_PRODUCT_TYPE_DM' -- Diabetes
              , 'REF_PRODUCT_TYPE_H3P' -- Health 3 Plus
              , 'REF_PRODUCT_TYPE_HPT' -- Health 3 Plus Tele
              , 'REF_PRODUCT_TYPE_LAM') then 'Others' -- Lamp
            when PRTY.SCMA_OID_PROD_TYPE = 'PRODUCT_TYPE_NHT' then 'Others' -- New Health Top Up
            else 'Individual'
          end as "TYPE"
        , POHO.SCMA_OID_BILL_PROVINCE
      from MR_POLICY POCY
        inner join MR_POLICY_PLAN POPL on POCY.POCY_OID = POPL.POCY_OID
        inner join MR_MEMBER_PLAN MEPL on POPL.POPL_OID = MEPL.POPL_OID
        inner join PD_PLAN PLAN on POPL.PLAN_OID = PLAN.PLAN_OID
        inner join RT_PRODUCT_TYPE PRTY on PRTY.PRTY_OID = PLAN.PRTY_OID
        inner join MR_POLICYHOLDER POHO on POCY.POHO_OID = POHO.POHO_OID
      where to_date('23062025', 'DDMMYYYY')
        between greatest(MEPL.EFF_DATE, POCY.EFF_DATE)
          and least(nvl(MEPL.TERM_DATE, MEPL.EXP_DATE), nvl(POCY.TERM_DATE, POCY.EXP_DATE))
    ) PO on MEMB.MEMB_OID = PO.MEMB_OID
    where nvl(MEMB.TERM_DATE, to_date('24062025', 'DDMMYYYY')) >= to_date('24062025', 'DDMMYYYY')
    and MEMB.EFF_DATE < to_date('24062025', 'DDMMYYYY')
    and ROWNUM <= 1000 
    ORDER BY MEMB.MBR_NO ASC 
    """, nativeQuery = true)
Collection<String> get_1000_active_members();


}

