package com.example.super_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.super_springboot.dto.SQL1;
import com.example.super_springboot.entity.ADODB_LOGSQL;

import java.util.Collection;


public interface ADODB_LOGSQLRepository1 extends JpaRepository<ADODB_LOGSQL, Integer> {

    @Query(value = "SELECT TIMER, SQL0, SQL1 FROM ADODB_LOGSQL", nativeQuery = true)
    Collection<ADODB_LOGSQL> Get_SQL0_SQL1();

    @Query(value = "SELECT SQL1 FROM ADODB_LOGSQL", nativeQuery = true)
    Collection<SQL1> Get_SQL1();
}
