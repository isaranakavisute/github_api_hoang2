package com.example.super_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.super_springboot.entity.MrPolicyPlan;
import java.util.Collection;

public interface MR_POLICY_PLAN_Repository extends JpaRepository<MrPolicyPlan, Integer> {

    @Query(value = "SELECT POPL_OID,POCY_OID,PLAN_OID FROM MR_POLICY_PLAN where POPL_OID=:POPL_OID", nativeQuery = true)
    Collection<MrPolicyPlan> get_POCY_OID(Long POPL_OID);

    @Query(value = """
        SELECT POPL_OID,POCY_OID,PLAN_OID
        FROM MR_POLICY_PLAN
        WHERE POPL_OID = :poplOid
        """, nativeQuery = true)
    Collection<MrPolicyPlan> getPolicyPlanByPoplOid(String poplOid);
}

