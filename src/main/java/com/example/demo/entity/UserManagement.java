package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_management")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserManagement {
    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @Column(name = "last_login", nullable = false)
    private LocalDateTime lastLogin;

    @Column(nullable = false)
    private String department;

    @Column(name = "sessions_attended", nullable = false)
    private Integer sessionsAttended = 0;

    @Column(name = "sessions_created", nullable = false)
    private Integer sessionsCreated = 0;

    @Column(name = "total_hours", nullable = false)
    private Integer totalHours = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;

    private String phone;

    private String avatar;

    @ManyToMany(mappedBy = "users")  // Inverse side of the many-to-many relationship
    @JsonIgnore
    private List<DemoSession> demoSessions;  // List of sessions the user is part of

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum UserRole {
        admin, employee
    }

    public enum UserStatus {
        active, inactive, pending
    }

    public enum SkillLevel {
        Beginner, Intermediate, Advanced
    }
}
