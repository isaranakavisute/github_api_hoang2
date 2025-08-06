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

import com.example.super_springboot.entity.VwPchiMobileClaimDetail;

public interface VwPchiMobileClaimDetail_Repository extends JpaRepository<VwPchiMobileClaimDetail, Integer> {

    @Query(value = "SELECT * from VW_PCHI_MOBILE_CLAIM_DETAIL WHERE MemberNo=:MBR_NO", nativeQuery = true)
    Collection<VwPchiMobileClaimDetail> get_VwPchiMobileClaimDetail_From_MBR_NO(String MBR_NO);
    
}

