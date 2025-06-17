package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Settings API Key Data Transfer Object")
public class SettingsApiKeyDTO {

    @Schema(description = "Unique identifier of the API key", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    @Schema(description = "Name of the API key", example = "Production API Key")
    private String name;

    @NotBlank(message = "API key is required")
    @Size(min = 1, max = 255, message = "API key must be between 1 and 255 characters")
    @Schema(description = "The API key value", example = "2312222222222222222222222y")
    private String key;

    @Schema(description = "Date when the API key was created")
    private LocalDate createdAt;

    @Schema(description = "Date when the API key was last used")
    private LocalDate lastUsed;
} 