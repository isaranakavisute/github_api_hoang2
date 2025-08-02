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

    @Query(value = """
        SELECT popl_oid 
        FROM (
            SELECT popl_oid 
            FROM mr_policy_plan a
            LEFT JOIN mr_policy b ON a.pocy_oid = b.pocy_oid
            WHERE b.pocy_no = :pocyNo
            ORDER BY popl_oid DESC
        )
        WHERE ROWNUM = 1
        """, nativeQuery = true)
    Long getLatestPoplByPocyNo(String pocyNo);

    @Query(value = """
        SELECT pt.scma_oid_product 
        FROM MR_POLICY_PLAN popl
        LEFT JOIN PD_PLAN pl ON popl.plan_oid = pl.plan_oid
        LEFT JOIN RT_PRODUCT_TYPE pt on pl.prty_oid= pt.prty_oid
        WHERE popl.popl_oid = :poplOid
        """, nativeQuery = true)
    String getProductTypeByPoplOid(Long poplOid);
}

