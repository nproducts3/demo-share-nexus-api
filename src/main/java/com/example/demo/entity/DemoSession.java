package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "demo_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoSession {
    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 100)
    private String technology;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false, length = 100)
    @JsonIgnoreProperties("demoSessions")
    private UserManagement createdBy;

    @Column(nullable = false)
    private Integer attendees = 0;

    @Column(name = "max_attendees", nullable = false)
    @Min(value = 1, message = "Maximum attendees must be at least 1")
    private Integer maxAttendees;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionStatus status;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyLevel difficulty;

    @Column(columnDefinition = "TEXT")
    private String prerequisites;

    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionType type;

    @Column(columnDefinition = "TEXT")
    private String feedback = "";  // Default empty string

    @Column(name = "sprint_name")
    private String sprintName = "";  // Default empty string

    @Column(name = "story_points")
    private Integer storyPoints = 0;  // Default to 0

    @Column(name = "number_of_tasks")
    private Integer numberOfTasks = 0;  // Default to 0

    @Column(name = "number_of_bugs")
    private Integer numberOfBugs = 0;  // Default to 0

    @Enumerated(EnumType.STRING)
    @Column(name = "current_status")
    private CurrentStatus currentStatus;

    @Column
    private Integer rating;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
        name = "demo_session_users", // Join table name
        joinColumns = @JoinColumn(name = "demo_session_id"), // Foreign key for DemoSession
        inverseJoinColumns = @JoinColumn(name = "user_id") // Foreign key for UserManagement
    )
    @JsonIgnoreProperties("demoSessions")
    private List<UserManagement> users; // Multiple users can be part of each session

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParticipantRole role;  // Role in the session

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum SessionStatus {
        upcoming, completed, cancelled
    }

    public enum DifficultyLevel {
        Beginner, Intermediate, Advanced
    }

    public enum SessionType {
        PROJECT_BASED("Project-based"),
        PRODUCT_BASED("Product-based");

        private final String displayName;

        SessionType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public enum CurrentStatus {
        Planning, In_Progress, Testing, Completed, On_Hold
    }

    // Enum for role
    public enum ParticipantRole {
        HOST, CO_HOST, ATTENDEE
    }
}
