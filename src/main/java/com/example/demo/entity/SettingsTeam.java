package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "settings_team")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsTeam {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "max_sessions_per_day", nullable = false)
    private Integer maxSessionsPerDay;

    @Column(name = "auto_approve_registrations", nullable = false)
    private Boolean autoApproveRegistrations;

    @Column(name = "require_manager_approval", nullable = false)
    private Boolean requireManagerApproval;

    @Column(name = "session_reminder_hours", nullable = false)
    private Integer sessionReminderHours;
} 