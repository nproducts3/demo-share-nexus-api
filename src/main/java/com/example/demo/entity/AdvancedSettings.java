package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "advanced_settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvancedSettings {

    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "session_timeout", nullable = false)
    private Integer sessionTimeout;

    @Column(name = "max_file_size", nullable = false)
    private Integer maxFileSize;

    @Column(name = "enable_debug_mode", nullable = false)
    private Boolean enableDebugMode;

    @Column(name = "auto_backup", nullable = false)
    private Boolean autoBackup;

    @Column(name = "maintenance_mode", nullable = false)
    private Boolean maintenanceMode;
} 