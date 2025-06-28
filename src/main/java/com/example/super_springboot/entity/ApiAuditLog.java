package com.example.super_springboot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "API_AUDIT_LOG")
public class ApiAuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "HIBERNATE_SEQUENCE", allocationSize = 1)
    @Column(name = "APIA_OID")
    private Long apiaOid;

    @Column(name = "METHOD")
    private String method;

    @Column(name = "RESOURCE_URL")
    private String resourceUrl;

    @Column(name = "REQUEST_PAYLOAD")
    private String requestPayload;

    @Column(name = "RESPONSE_PAYLOAD")
    private String responsePayload;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CRT_DATE")
    private LocalDateTime crtDate;
}
