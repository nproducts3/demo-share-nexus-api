package com.example.demo.dto;

import com.example.demo.entity.DemoSession.SessionStatus;
import com.example.demo.entity.DemoSession.DifficultyLevel;
import com.example.demo.entity.DemoSession.SessionType;
import com.example.demo.entity.DemoSession.CurrentStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class DemoSessionDTO {
    private String id;

    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @Size(max = 100, message = "Technology must not exceed 100 characters")
    private String technology;

    private LocalDate date;

    private LocalTime time;

    private String description;

    @Size(max = 100, message = "Created by must not exceed 100 characters")
    private String createdBy;

    private Integer attendees = 0;

    @Min(value = 1, message = "Maximum attendees must be at least 1")
    private Integer maxAttendees;

    private SessionStatus status;

    @Size(max = 255, message = "Location must not exceed 255 characters")
    private String location;

    private DifficultyLevel difficulty;

    private String prerequisites;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer duration;

    private SessionType type;

    @Size(max = 1000, message = "Feedback must not exceed 1000 characters")
    private String feedback = "";  // Default empty string

    @Size(max = 100, message = "Sprint name must not exceed 100 characters")
    private String sprintName = "";  // Default empty string

    @Min(value = 0, message = "Story points must be non-negative")
    private Integer storyPoints = 0;  // Default to 0

    @Min(value = 0, message = "Number of tasks must be non-negative")
    private Integer numberOfTasks = 0;  // Default to 0

    @Min(value = 0, message = "Number of bugs must be non-negative")
    private Integer numberOfBugs = 0;  // Default to 0

    private CurrentStatus currentStatus;

    private List<String> userIds;  // List of user IDs for the foreign key


    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private Integer rating;

    @NotNull(message = "Role is required")
    private ParticipantRole role;  // Role in the session

    // Enum for role
    public enum ParticipantRole {
        HOST, CO_HOST, ATTENDEE
    }
}