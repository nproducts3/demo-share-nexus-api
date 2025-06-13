package com.example.demo.dto;

import com.example.demo.entity.UserManagement.UserRole;
import com.example.demo.entity.UserManagement.UserStatus;
import com.example.demo.entity.UserManagement.SkillLevel;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserManagementDTO {
    private String id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;

    @NotNull(message = "Role is required")
    private UserRole role;

    @NotNull(message = "Status is required")
    private UserStatus status;

    @NotNull(message = "Join date is required")
    @PastOrPresent(message = "Join date must be in the past or present")
    private LocalDate joinDate;

    @NotNull(message = "Last login is required")
    private LocalDateTime lastLogin;

    @NotBlank(message = "Department is required")
    @Size(max = 100, message = "Department must not exceed 100 characters")
    private String department;

    @PositiveOrZero(message = "Sessions attended must be zero or positive")
    private Integer sessionsAttended = 0;

    @PositiveOrZero(message = "Sessions created must be zero or positive")
    private Integer sessionsCreated = 0;

    @PositiveOrZero(message = "Total hours must be zero or positive")
    private Integer totalHours = 0;

    @NotNull(message = "Skill level is required")
    private SkillLevel skillLevel;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String phone;

    @Size(max = 255, message = "Avatar URL must not exceed 255 characters")
    private String avatar;
} 