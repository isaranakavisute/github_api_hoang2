package com.example.super_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.super_springboot.entity.PdBenHead;
import java.util.Collection;

public interface PD_BEN_HEAD_Repository extends JpaRepository<PdBenHead, Integer> {

    @Query(value = "SELECT BEHD_OID,BEN_HEAD,SCMA_OID_BEN_TYPE FROM PD_BEN_HEAD where BEHD_OID=:BEHD_OID", nativeQuery = true)
    Collection<PdBenHead> get_BEN_HEAD(Long BEHD_OID);

}

