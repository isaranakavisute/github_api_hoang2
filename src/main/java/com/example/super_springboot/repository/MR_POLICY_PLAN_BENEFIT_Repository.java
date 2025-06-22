package com.example.super_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.super_springboot.entity.MrPolicyPlanBenefit;

import java.util.Collection;

public interface MR_POLICY_PLAN_BENEFIT_Repository extends JpaRepository<MrPolicyPlanBenefit, Integer> {

    @Query(value = "SELECT POBE_OID,PLBE_OID FROM MR_POLICY_PLAN_BENEFIT where POBE_OID=:POBE_OID", nativeQuery = true)
    Collection<MrPolicyPlanBenefit> get_PLBE_OID(Long POBE_OID);
}
