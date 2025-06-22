package com.example.super_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.super_springboot.entity.PdPlanBenefit;
import java.util.Collection;

public interface PD_PLAN_BENEFIT_Repository extends JpaRepository<PdPlanBenefit, Integer> {

    @Query(value = "SELECT PLBE_OID,PLLI_OID,BEHD_OID FROM PD_PLAN_BENEFIT where PLBE_OID=:PLBE_OID", nativeQuery = true)
    Collection<PdPlanBenefit> get_BEHD_OID(Long PLBE_OID);
}
