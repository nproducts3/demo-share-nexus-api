package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Settings API Key Data Transfer Object")
public class SettingsApiKeyDTO {

    @Schema(description = "Unique identifier of the API key", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Name of the API key", example = "Production API Key")
    private String name;

    @Schema(description = "The API key value (UUID)", example = "123e4567-e89b-12d3-a456-426614174000")
    private String key;

    @JsonIgnore
    @Schema(description = "Timestamp when the API key was created", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonIgnore
    @Schema(description = "Timestamp when the API key was last used", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime lastUsed;
} 