package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "settings_team")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsTeam {

    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "max_sessions_per_day", nullable = false)
    private Integer maxSessionsPerDay;

    @Column(name = "auto_approve_registrations", nullable = false)
    private Boolean autoApproveRegistrations;

    @Column(name = "require_manager_approval", nullable = false)
    private Boolean requireManagerApproval;

    @Column(name = "session_reminder_hours", nullable = false)
    private Integer sessionReminderHours;
} 