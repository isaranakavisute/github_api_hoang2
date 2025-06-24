//package com.example.super_springboot;
package com.example.super_springboot.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import com.example.super_springboot.entity.PdPlanLimit;

public interface PD_PLAN_LIMIT_Repository extends JpaRepository<PdPlanLimit, Integer> {

    @Query(value = "SELECT PLLI_OID,PLAN_OID,VIS_YR,VIS_DAY,AMT_YR,AMT_DIS_YR  FROM PD_PLAN_LIMIT where PLAN_OID=:PLAN_OID", nativeQuery = true)
    Collection<PdPlanLimit> get_PLAN_LIMIT(Long PLAN_OID);




   

}

