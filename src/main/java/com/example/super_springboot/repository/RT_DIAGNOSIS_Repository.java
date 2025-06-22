package com.example.super_springboot.repository;

import com.example.super_springboot.entity.RtDiagnosis;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RT_DIAGNOSIS_Repository extends JpaRepository<RtDiagnosis, Long> {

    @Query(value = """
        SELECT DIAG_OID, DIAG_CODE, DIAG_DESC
        FROM RT_DIAGNOSIS
        WHERE DIAG_OID = :diagOid
        """, nativeQuery = true)
    Collection<RtDiagnosis> getRtDiagnosisByOid(String diagOid);
}