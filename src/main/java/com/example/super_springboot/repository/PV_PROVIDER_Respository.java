package com.example.super_springboot.repository;

import com.example.super_springboot.entity.PvProvider;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PV_PROVIDER_Respository extends JpaRepository<PvProvider, Long> {

    @Query(value = """
        SELECT PROV_OID, PROV_CODE, PROV_NAME
        FROM PV_PROVIDER
        WHERE PROV_NAME = :provName
        """, nativeQuery = true)
    Collection<PvProvider> getProvByProvName(String provName);

    @Query(value = """
        SELECT prov_oid
        FROM pv_provider
        WHERE prov_name = :provName
        """, nativeQuery = true)
    Long getProviderOidByName(@Param("provName") String provName);
}