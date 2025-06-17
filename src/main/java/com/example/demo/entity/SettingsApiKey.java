package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "settings_api_keys")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsApiKey {

    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "api_key", nullable = false, unique = true, length = 255)
    private String key;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "last_used", nullable = false)
    private LocalDate lastUsed;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDate.now();
        }
        if (lastUsed == null) {
            lastUsed = LocalDate.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        lastUsed = LocalDate.now();
    }
} 