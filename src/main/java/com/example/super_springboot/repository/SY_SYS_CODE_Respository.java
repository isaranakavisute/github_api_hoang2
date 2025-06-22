package com.example.super_springboot.repository;

import com.example.super_springboot.entity.SySysCode;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SY_SYS_CODE_Respository extends JpaRepository<SySysCode, Long> {

    @Query(value = """
        SELECT SCMA_OID, SYS_TYPE, SYS_CODE, SEQ, FLAG, CRT_USER, CRT_DATE, UPD_USER, UPD_DATE
        FROM SY_SYS_CODE
        WHERE SCMA_OID = :scmaOid
        """, nativeQuery = true)
    Collection<SySysCode> getSySysCodeBySmaOid(String scmaOid);
}