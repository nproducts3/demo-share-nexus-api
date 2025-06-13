package com.example.demo.service;

import com.example.demo.entity.DemoSession;
import com.example.demo.dto.DemoSessionDTO;
import com.example.demo.repository.DemoSessionRepository;
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

    public DemoSessionService(DemoSessionRepository repository) {
        this.repository = repository;
    }

    public DemoSession createSession(DemoSessionDTO dto) {
        DemoSession session = new DemoSession();
        BeanUtils.copyProperties(dto, session);
        session.setId(UUID.randomUUID().toString());
        return repository.save(session);
    }

    public DemoSession updateSession(String id, DemoSessionDTO dto) {
        DemoSession session = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Session not found with id: " + id));
        
        // Update required fields
        if (dto.getTitle() != null) session.setTitle(dto.getTitle());
        if (dto.getTechnology() != null) session.setTechnology(dto.getTechnology());
        if (dto.getDate() != null) session.setDate(dto.getDate());
        if (dto.getTime() != null) session.setTime(dto.getTime());
        if (dto.getDescription() != null) session.setDescription(dto.getDescription());
        if (dto.getCreatedBy() != null) session.setCreatedBy(dto.getCreatedBy());
        if (dto.getMaxAttendees() != null) session.setMaxAttendees(dto.getMaxAttendees());
        if (dto.getStatus() != null) session.setStatus(dto.getStatus());
        if (dto.getLocation() != null) session.setLocation(dto.getLocation());
        if (dto.getDifficulty() != null) session.setDifficulty(dto.getDifficulty());
        if (dto.getPrerequisites() != null) session.setPrerequisites(dto.getPrerequisites());
        if (dto.getDuration() != null) session.setDuration(dto.getDuration());
        if (dto.getType() != null) session.setType(dto.getType());
        if (dto.getCurrentStatus() != null) session.setCurrentStatus(dto.getCurrentStatus());
        if (dto.getRating() != null) session.setRating(dto.getRating());
        if (dto.getAttendees() != null) session.setAttendees(dto.getAttendees());

        // Handle fields that can be empty strings or zero values
        // session.setFeedback(dto.getFeedback() != null ? dto.getFeedback() : "");
        // session.setSprintName(dto.getSprintName() != null ? dto.getSprintName() : "");
        // session.setStoryPoints(dto.getStoryPoints() != null ? dto.getStoryPoints() : 0);
        // session.setNumberOfTasks(dto.getNumberOfTasks() != null ? dto.getNumberOfTasks() : 0);
        // session.setNumberOfBugs(dto.getNumberOfBugs() != null ? dto.getNumberOfBugs() : 0);

        if (dto.getFeedback() != null) session.setFeedback(dto.getFeedback());
if (dto.getSprintName() != null) session.setSprintName(dto.getSprintName());
if (dto.getStoryPoints() != null) session.setStoryPoints(dto.getStoryPoints());
if (dto.getNumberOfTasks() != null) session.setNumberOfTasks(dto.getNumberOfTasks());
if (dto.getNumberOfBugs() != null) session.setNumberOfBugs(dto.getNumberOfBugs());
        System.out.println("Updating session with data: " + dto);
        System.out.println("Updated session: " + session);
        return repository.save(session);
    }

    public DemoSession getSession(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Session not found with id: " + id));
    }

    public Page<DemoSession> getAllSessions(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void deleteSession(String id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Session not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public Page<DemoSession> getUpcomingSessions(Pageable pageable) {
        return repository.findByDateGreaterThanEqual(LocalDate.now(), pageable);
    }

    public Page<DemoSession> getSessionsByTechnology(String technology, Pageable pageable) {
        return repository.findByTechnologyContainingIgnoreCase(technology, pageable);
    }

    public Page<DemoSession> getSessionsByDifficulty(DemoSession.DifficultyLevel difficulty, Pageable pageable) {
        return repository.findByDifficulty(difficulty, pageable);
    }
}