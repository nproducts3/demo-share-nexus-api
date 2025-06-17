package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Notification Settings Data Transfer Object")
public class NotificationSettingsDTO {
    
    @Schema(description = "Unique identifier of the notification settings", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @NotNull(message = "Email notifications setting is required")
    @Schema(description = "Whether email notifications are enabled", example = "true")
    private Boolean emailNotifications;

    @NotNull(message = "Push notifications setting is required")
    @Schema(description = "Whether push notifications are enabled", example = "true")
    private Boolean pushNotifications;

    @NotNull(message = "Session reminders setting is required")
    @Schema(description = "Whether session reminders are enabled", example = "true")
    private Boolean sessionReminders;

    @NotNull(message = "Weekly reports setting is required")
    @Schema(description = "Whether weekly reports are enabled", example = "false")
    private Boolean weeklyReports;

    @NotNull(message = "Marketing emails setting is required")
    @Schema(description = "Whether marketing emails are enabled", example = "false")
    private Boolean marketingEmails;
} 