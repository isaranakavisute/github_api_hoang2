package com.example.super_springboot.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.super_springboot.entity.ClLine;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public interface CL_LINE_Repository extends JpaRepository<ClLine, Long> {

    // @Query(value = "SELECT CLLI_OID,CLAM_OID,LINE_NO,LINE_REV_NO,REV_NO,STAGE_IDX,RCV_DATE,MEMB_OID,INCUR_DATE_FROM,INCUR_DATE_TO,DAYS,BEN_POPL_OID,POPL_OID,SCMA_OID_PRODUCT,SCMA_OID_CL_TYPE,SCMA_OID_CL_PAY_TO,PROV_OID,PROV_NAME,SCMA_OID_COUNTRY_TREATMENT,PAY_NAME1,PAY_NAME2,PAY_ADDR1,PAY_ADDR2,PAY_ADDR3,PAY_ADDR4,SCMA_OID_BED_TYPE,BEHD_OID,DIAG_OID,SYMPTOM_DATE,SCMA_OID_YN_ER,SCMA_OID_YN_TREAT_PLAN,SCMA_OID_YN_WP,SCMA_OID_CCY_PRES,FX_RATE,ORG_PRES_AMT,PRES_AMT,ORG_ADJ_PRES_AMT,ADJ_PRES_AMT,MAN_REJ_AMT,OVR_DED_AMT,OVR_APP_AMT,APP_AMT,BEN_SPEND,PRIOR_PAID,SCMA_OID_CL_LINE_STATUS,SCMA_OID_CL_PAYMENT_METHOD,SCMA_OID_COUNTRY_PAY,PAYEE,PAY_DATE,PAY_FX_RATE,PAY_AMT,SCMA_OID_CCY_PAY,REMARK,PEND_VAL,SCMA_OID_PAY_PROVINCE,PAY_PRINT_IN_OTHER_LANG_IND,HOSPITAL_NUMBER,CL_PAY_VOUCHER,HOSP_SUB_NO,PAY_ZIP_CDE,SCMA_OID_YN_ACC,SCMA_OID_YN_HOSPITAL_PDN,CRT_USER,CRT_DATE,UPD_USER,UPD_DATE FROM CL_LINE where CLAM_OID=:CLAM_OID ORDER BY LINE_NO ASC", nativeQuery = true)
    // Collection<ClLine> get_CL_Line(Long CLAM_OID);

    @Query(value = """
        SELECT COALESCE(MAX(line_no) + 1, 1)
        FROM cl_line
        WHERE clam_oid = :clamOid
        """, nativeQuery = true)
    Integer findNextLineNo(@Param("clamOid") Long clamOid);

    @Query(value = "SELECT CLLI_OID,CLAM_OID,INCUR_DATE_FROM,INCUR_DATE_TO,SCMA_OID_CL_TYPE,SCMA_OID_BED_TYPE,PRES_AMT,APP_AMT,PRIOR_PAID,SCMA_OID_CL_LINE_STATUS,PAY_DATE,PAY_AMT,LINE_NO,PAYEE,MEMB_OID,CHQ_DATE,RCV_DATE, DAYS,PAY_ADDR1,PAY_ADDR2,PAY_ADDR3,PAY_ADDR4,SCMA_OID_PAY_PROVINCE,SCMA_OID_COUNTRY_PAY,SCMA_OID_CL_PAYMENT_METHOD,PAY_ZIP_CDE,LINE_REV_NO,PROV_OID,PROV_NAME " + "FROM CL_LINE where CLAM_OID=:CLAM_OID AND SCMA_OID_CL_LINE_STATUS != 'CL_LINE_STATUS_RV' ORDER BY LINE_NO ASC", nativeQuery = true)
    Collection<ClLine> get_CL_Line(Long CLAM_OID);

    @Query(value = "SELECT CLLI_OID,CLAM_OID,INCUR_DATE_FROM,INCUR_DATE_TO,SCMA_OID_CL_TYPE,SCMA_OID_BED_TYPE,PRES_AMT,APP_AMT,PRIOR_PAID,SCMA_OID_CL_LINE_STATUS,PAY_DATE,PAY_AMT,LINE_NO,PAYEE,MEMB_OID,CHQ_DATE,RCV_DATE,DAYS,PAY_ADDR1,PAY_ADDR2,PAY_ADDR3,PAY_ADDR4,SCMA_OID_PAY_PROVINCE,SCMA_OID_COUNTRY_PAY,SCMA_OID_CL_PAYMENT_METHOD,PAY_ZIP_CDE,LINE_REV_NO,PROV_OID,PROV_NAME " + "FROM CL_LINE where MEMB_OID=:MEMB_OID AND SCMA_OID_CL_LINE_STATUS != 'CL_LINE_STATUS_RV' ORDER BY LINE_NO ASC", nativeQuery = true)
    Collection<ClLine> get_CL_Line_From_Memb(Long MEMB_OID);

    
  

  

   
   

   

  

   
  

  
 
   
   
}
