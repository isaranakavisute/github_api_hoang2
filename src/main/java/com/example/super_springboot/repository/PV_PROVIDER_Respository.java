package com.example.super_springboot.repository;

import com.example.super_springboot.entity.PvProvider;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PV_PROVIDER_Respository extends JpaRepository<PvProvider, Long> {

    @Query(value = """
        SELECT PROV_OID, PROV_CODE, PROV_NAME
        FROM PV_PROVIDER
        WHERE PROV_NAME = :provName
        """, nativeQuery = true)
    Collection<PvProvider> getProvByProvName(String provName);
}