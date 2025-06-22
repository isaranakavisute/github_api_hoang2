package com.example.super_springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.super_springboot.entity.MrPolicy;
import java.util.Collection;

public interface MR_POLICY_Repository extends JpaRepository<MrPolicy, Integer> {

    @Query(value = "SELECT POCY_OID,POHO_OID,POCY_NO,LMG_NO FROM MR_POLICY where POCY_OID=:POCY_OID", nativeQuery = true)
    Collection<MrPolicy> get_policy_no(Long POCY_OID);

    @Query(value = """
        SELECT POCY_OID,POHO_OID,POCY_NO,LMG_NO
        FROM MR_POLICY
        WHERE POCY_NO = :pocyNo
        """, nativeQuery = true)
    Collection<MrPolicy> getPolicyByPocyNo(String pocyNo);
}
