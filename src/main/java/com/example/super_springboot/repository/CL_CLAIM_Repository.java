package com.example.super_springboot.repository;

import com.example.super_springboot.entity.ClClaim;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CL_CLAIM_Repository extends JpaRepository<ClClaim, Long> {

    @Query(value = "SELECT CLAM_OID,CL_NO,SCMA_OID_CL_STATUS,SCMA_OID_YN_FORCE_PAY,REMARK,CRT_USER,CRT_DATE,UPD_USER,UPD_DATE FROM CL_CLAIM where CL_NO=:CL_NO", nativeQuery = true)
    Collection<ClClaim> get_CL_CLaim(String CL_NO);
    
    @Query(
        value = """
            SELECT LPAD(TO_CHAR(SUBSTR(MAX(cl_no), 7) + 1), 4, '0')
            FROM cl_claim
            WHERE SUBSTR(cl_no, 1, 6) = :prefix
              AND ASCII(SUBSTR(cl_no, 10)) BETWEEN 48 AND 57
            """,
        nativeQuery = true
    )
    String findNextClaimSuffix(@Param("prefix") String prefix);

     @Query(value = "SELECT CLAM_OID,CL_NO,SCMA_OID_CL_STATUS,REMARK FROM CL_CLAIM where CLAM_OID=:CLAM_OID", nativeQuery = true)
    Collection<ClClaim> get_CL_CLaim_From_CLAIM_OID(Long CLAM_OID);

    
}