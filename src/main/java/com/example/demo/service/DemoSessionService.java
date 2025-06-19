package com.example.demo.service;

import com.example.demo.entity.DemoSession;
import com.example.demo.dto.DemoSessionDTO;
import com.example.demo.entity.UserManagement;
import com.example.demo.repository.DemoSessionRepository;
import com.example.demo.repository.UserManagementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DemoSessionService {
    private final DemoSessionRepository repository;
    private final UserManagementRepository userManagementRepository;

    public DemoSessionService(DemoSessionRepository repository, UserManagementRepository userManagementRepository) {
        this.repository = repository;
        this.userManagementRepository = userManagementRepository;
    }

    /**
     * Create a new demo session
     * 
     * @param dto The DTO containing session details
     * @return The created DemoSession
     */
    public DemoSession createSession(DemoSessionDTO dto) {
        // Validate and fetch the users by their IDs
        List<UserManagement> users = userManagementRepository.findAllById(dto.getUserIds());

        // If some user IDs are invalid, throw an exception
        if (users.size() != dto.getUserIds().size()) {
            throw new EntityNotFoundException("One or more users not found");
        }

        // Create a new DemoSession object
        DemoSession session = new DemoSession();

        // Manually set properties to avoid BeanUtils issues with different field types
        session.setTitle(dto.getTitle());
        session.setTechnology(dto.getTechnology());
        session.setDate(dto.getDate());
        session.setTime(dto.getTime());
        session.setDescription(dto.getDescription());
        session.setCreatedBy(dto.getCreatedBy());
        session.setAttendees(dto.getAttendees());
        session.setMaxAttendees(dto.getMaxAttendees());
        session.setStatus(dto.getStatus());
        session.setLocation(dto.getLocation());
        session.setDifficulty(dto.getDifficulty());
        session.setPrerequisites(dto.getPrerequisites());
        session.setDuration(dto.getDuration());
        session.setType(dto.getType());
        session.setFeedback(dto.getFeedback());
        session.setSprintName(dto.getSprintName());
        session.setStoryPoints(dto.getStoryPoints());
        session.setNumberOfTasks(dto.getNumberOfTasks());
        session.setNumberOfBugs(dto.getNumberOfBugs());
        session.setCurrentStatus(dto.getCurrentStatus());
        session.setRating(dto.getRating());

        // Set the generated session ID and users associated with the session
        session.setId(UUID.randomUUID().toString());
        session.setUsers(users);  // Set the list of users

        // Ensure role is correctly set as enum
        session.setRole(DemoSession.ParticipantRole.valueOf(dto.getRole().name()));

        // Save the demo session to the repository
        return repository.save(session);
    }

    /**
     * Update an existing demo session
     * 
     * @param id  The ID of the session to update
     * @param dto The DTO containing the updated session details
     * @return The updated DemoSession
     */
    public DemoSession updateSession(String id, DemoSessionDTO dto) {
        // Retrieve the existing session from the repository
        DemoSession session = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Session not found with id: " + id));

        // Retrieve the list of users by their IDs from the UserManagement table
        List<UserManagement> users = userManagementRepository.findAllById(dto.getUserIds());

        // If some user IDs are invalid, throw an exception
        if (users.size() != dto.getUserIds().size()) {
            throw new EntityNotFoundException("One or more users not found");
        }

        // Update the session fields with the new data from the DTO
        session.setUsers(users);  // Update the list of users
        session.setRole(DemoSession.ParticipantRole.valueOf(dto.getRole().name()));  // Update the role with correct enum type

        // Use BeanUtils to copy the properties from DTO to the session entity
        BeanUtils.copyProperties(dto, session, "id", "users", "role");  // Exclude the id, users, and role from BeanUtils copy

        // Save the updated session to the repository
        return repository.save(session);
    }

    /**
     * Retrieve a demo session by ID
     * 
     * @param id The ID of the session to retrieve
     * @return The DemoSession
     */
    public DemoSession getSession(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Session not found with id: " + id));
    }

    /**
     * Retrieve all demo sessions with pagination
     * 
     * @param pageable Pageable object to support pagination
     * @return A page of DemoSession
     */
    public Page<DemoSession> getAllSessions(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Delete a demo session by ID
     * 
     * @param id The ID of the session to delete
     */
    public void deleteSession(String id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Session not found with id: " + id);
        }
        repository.deleteById(id);
    }

    /**
     * Retrieve all upcoming demo sessions (sessions from today onwards) with pagination
     * 
     * @param pageable Pageable object to support pagination
     * @return A page of upcoming DemoSession
     */
    public Page<DemoSession> getUpcomingSessions(Pageable pageable) {
        return repository.findByDateGreaterThanEqual(LocalDate.now(), pageable);
    }

    /**
     * Retrieve demo sessions by technology with pagination
     * 
     * @param technology The technology for filtering sessions
     * @param pageable   Pageable object to support pagination
     * @return A page of DemoSession filtered by technology
     */
    public Page<DemoSession> getSessionsByTechnology(String technology, Pageable pageable) {
        return repository.findByTechnologyContainingIgnoreCase(technology, pageable);
    }

    /**
     * Retrieve demo sessions by difficulty level with pagination
     * 
     * @param difficulty The difficulty level for filtering sessions
     * @param pageable   Pageable object to support pagination
     * @return A page of DemoSession filtered by difficulty level
     */
    public Page<DemoSession> getSessionsByDifficulty(DemoSession.DifficultyLevel difficulty, Pageable pageable) {
        return repository.findByDifficulty(difficulty, pageable);
    }
}
