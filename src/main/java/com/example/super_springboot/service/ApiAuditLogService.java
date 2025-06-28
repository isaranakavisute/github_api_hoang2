package com.example.super_springboot.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.super_springboot.entity.ApiAuditLog;
import com.example.super_springboot.repository.API_AUDIT_LOG_Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiAuditLogService {
    private final API_AUDIT_LOG_Repository auditLogRepository;
    private final ObjectMapper objectMapper;

    public void logApiCall(String method, String url, Object request, Object response, String status) {
        try {

        String requestJson = objectMapper.writeValueAsString(request);
        String responseJson = objectMapper.writeValueAsString(response);

        ApiAuditLog log = new ApiAuditLog();
        log.setMethod(method);
        log.setResourceUrl(StringUtils.isNotBlank(url) && url.length() > 2000 ? url.substring(0, 2000) : url);
        log.setRequestPayload(StringUtils.isNotBlank(requestJson) && requestJson.length() > 2000 ? requestJson.substring(0, 2000) : requestJson);
        log.setResponsePayload(StringUtils.isNotBlank(responseJson) && responseJson.length() > 2000 ? responseJson.substring(0, 2000) : responseJson);
        log.setStatus(status);
        log.setCrtDate(LocalDateTime.now());

        auditLogRepository.save(log);
        } catch (Exception e) {
            // Logging failure should not break business flow
            System.err.println("Failed to save audit log: " + e.getMessage());
        }
    }
}
