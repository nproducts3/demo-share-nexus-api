package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Advanced Settings Data Transfer Object")
public class AdvancedSettingsDTO {
    
    private String id;

    @NotNull(message = "Session timeout is required")
    @Min(value = 1, message = "Session timeout must be at least 1 minute")
    @Schema(description = "Session timeout in minutes", example = "30")
    private Integer sessionTimeout;

    @NotNull(message = "Maximum file size is required")
    @Min(value = 1, message = "Maximum file size must be at least 1 MB")
    @Schema(description = "Maximum file size in MB", example = "10")
    private Integer maxFileSize;

    @NotNull(message = "Debug mode setting is required")
    @Schema(description = "Whether debug mode is enabled", example = "false")
    private Boolean enableDebugMode;

    @NotNull(message = "Auto backup setting is required")
    @Schema(description = "Whether automatic backup is enabled", example = "true")
    private Boolean autoBackup;

    @NotNull(message = "Maintenance mode setting is required")
    @Schema(description = "Whether maintenance mode is enabled", example = "false")
    private Boolean maintenanceMode;
} 