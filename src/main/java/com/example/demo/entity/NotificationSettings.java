package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification_settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSettings {

    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "email_notifications", nullable = false)
    private Boolean emailNotifications;

    @Column(name = "push_notifications", nullable = false)
    private Boolean pushNotifications;

    @Column(name = "session_reminders", nullable = false)
    private Boolean sessionReminders;

    @Column(name = "weekly_reports", nullable = false)
    private Boolean weeklyReports;

    @Column(name = "marketing_emails", nullable = false)
    private Boolean marketingEmails;
} 