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


import com.example.super_springboot.entity.VwPchiMobilePersonalInfo;

public interface VwPchiMobilePersonalInfo_Repository extends JpaRepository<VwPchiMobilePersonalInfo, Integer> {

    @Query(value = "SELECT * from VW_PCHI_MOBILE_PERSONAL_INFO WHERE UPD_DATE like :UPD_DATE OR UPD_DATE like :daybefore", nativeQuery = true)
    Collection<VwPchiMobilePersonalInfo> get_members_from_upd_date(String UPD_DATE, String daybefore);
    
}

