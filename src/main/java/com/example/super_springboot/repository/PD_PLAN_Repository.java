package com.example.super_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.super_springboot.entity.PdPlan;

import java.util.Collection;

public interface PD_PLAN_Repository extends JpaRepository<PdPlan, Integer> {

    @Query(value = "SELECT PLAN_OID, PLAN_ID,PLAN_NO,PLAN_DESC FROM PD_PLAN where PLAN_OID=:PLAN_OID", nativeQuery = true)
    Collection<PdPlan> get_PLAN_ID(Long PLAN_OID);
}

