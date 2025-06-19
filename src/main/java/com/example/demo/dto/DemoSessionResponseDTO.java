package com.example.demo.dto;

import com.example.demo.entity.DemoSession;
import com.example.demo.entity.UserManagement;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DemoSessionResponseDTO {
    private String id;
    private String title;
    private String technology;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private UserManagement createdBy;
    private Integer attendees;
    private Integer maxAttendees;
    private DemoSession.SessionStatus status;
    private String location;
    private DemoSession.DifficultyLevel difficulty;
    private String prerequisites;
    private Integer duration;
    private DemoSession.SessionType type;
    private String feedback;
    private String sprintName;
    private Integer storyPoints;
    private Integer numberOfTasks;
    private Integer numberOfBugs;
    private DemoSession.CurrentStatus currentStatus;
    private Integer rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> userIds; // Only user IDs, not full user objects
    private DemoSession.ParticipantRole role;

    public static DemoSessionResponseDTO fromEntity(DemoSession session) {
        DemoSessionResponseDTO dto = new DemoSessionResponseDTO();
        dto.setId(session.getId());
        dto.setTitle(session.getTitle());
        dto.setTechnology(session.getTechnology());
        dto.setDate(session.getDate());
        dto.setTime(session.getTime());
        dto.setDescription(session.getDescription());
        dto.setCreatedBy(session.getCreatedBy());
        dto.setAttendees(session.getAttendees());
        dto.setMaxAttendees(session.getMaxAttendees());
        dto.setStatus(session.getStatus());
        dto.setLocation(session.getLocation());
        dto.setDifficulty(session.getDifficulty());
        dto.setPrerequisites(session.getPrerequisites());
        dto.setDuration(session.getDuration());
        dto.setType(session.getType());
        dto.setFeedback(session.getFeedback());
        dto.setSprintName(session.getSprintName());
        dto.setStoryPoints(session.getStoryPoints());
        dto.setNumberOfTasks(session.getNumberOfTasks());
        dto.setNumberOfBugs(session.getNumberOfBugs());
        dto.setCurrentStatus(session.getCurrentStatus());
        dto.setRating(session.getRating());
        dto.setCreatedAt(session.getCreatedAt());
        dto.setUpdatedAt(session.getUpdatedAt());
        dto.setRole(session.getRole());
        
        // Extract user IDs from the users list
        if (session.getUsers() != null) {
            List<String> userIds = session.getUsers().stream()
                    .map(UserManagement::getId)
                    .collect(Collectors.toList());
            dto.setUserIds(userIds);
        }
        
        return dto;
    }
} 