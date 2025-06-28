package com.example.super_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.super_springboot.entity.ApiAuditLog;

public interface API_AUDIT_LOG_Repository extends JpaRepository<ApiAuditLog, Long> {

}