package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Team Settings Data Transfer Object")
public class SettingsTeamDTO {
    
    @Schema(description = "Unique identifier of the team settings", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @NotNull(message = "Maximum sessions per day is required")
    @Min(value = 1, message = "Maximum sessions per day must be at least 1")
    @Schema(description = "Maximum number of sessions allowed per day", example = "5")
    private Integer maxSessionsPerDay;

    @NotNull(message = "Auto approve registrations setting is required")
    @Schema(description = "Whether to automatically approve new registrations", example = "true")
    private Boolean autoApproveRegistrations;

    @NotNull(message = "Manager approval requirement setting is required")
    @Schema(description = "Whether manager approval is required for sessions", example = "false")
    private Boolean requireManagerApproval;

    @NotNull(message = "Session reminder hours is required")
    @Min(value = 1, message = "Session reminder hours must be at least 1")
    @Schema(description = "Number of hours before session to send reminder", example = "24")
    private Integer sessionReminderHours;
} 