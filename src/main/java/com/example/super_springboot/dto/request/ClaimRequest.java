package com.example.super_springboot.dto.request;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ClaimRequest {

    @NotBlank(message = "member_no is required")
    @Pattern(regexp = "\\d{9}", message = "member_no must be 9 digits")
    private String member_no;

    @NotBlank(message = "policy_no is required")
    @Pattern(regexp = "\\d{14}", message = "policy_no must be 14 digits")
    private String policy_no;

    @NotBlank(message = "remarks is required")
    @Size(max = 400, message = "Remarks must not exceed 4000 characters")
    private String remarks;

    @NotBlank(message = "total_billed is required")
    private String total_billed;

    @NotNull(message = "start date is required")
    private String start;

    @NotNull(message = "finish date is required")
    private String finish;

    @NotNull(message = "member_name is required")
    private String member_name;

    @NotNull(message = "provider is required")
    private String provider;
}

