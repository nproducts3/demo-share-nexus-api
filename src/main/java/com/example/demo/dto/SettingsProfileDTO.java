package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Settings Profile Data Transfer Object")
public class SettingsProfileDTO {
    
    @Schema(description = "Unique identifier of the settings profile", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @NotBlank(message = "Name is required")
    @Schema(description = "Name of the profile owner", example = "John Doe")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(description = "Email address of the profile owner", example = "john.doe@example.com")
    private String email;

    @NotBlank(message = "Phone is required")
    @Schema(description = "Phone number of the profile owner", example = "+1234567890")
    private String phone;

    @NotBlank(message = "Department is required")
    @Schema(description = "Department of the profile owner", example = "Engineering")
    private String department;

    @NotBlank(message = "Company is required")
    @Schema(description = "Company name", example = "Tech Corp")
    private String company;

    @NotBlank(message = "Bio is required")
    @Schema(description = "Biography or description of the profile owner", example = "Experienced software engineer with 5 years of experience")
    private String bio;
} 