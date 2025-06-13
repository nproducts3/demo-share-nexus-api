package com.example.demo.service;

import com.example.demo.entity.UserManagement;
import com.example.demo.dto.UserManagementDTO;
import com.example.demo.repository.UserManagementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserManagementService {
    private final UserManagementRepository repository;

    public UserManagementService(UserManagementRepository repository) {
        this.repository = repository;
    }

    public UserManagement createUser(UserManagementDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists: " + dto.getEmail());
        }

        UserManagement user = new UserManagement();
        BeanUtils.copyProperties(dto, user);
        user.setId(UUID.randomUUID().toString());
        return repository.save(user);
    }

    public UserManagement updateUser(String id, UserManagementDTO dto) {
        UserManagement user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        if (!user.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists: " + dto.getEmail());
        }

        BeanUtils.copyProperties(dto, user, "id", "createdAt", "updatedAt");
        return repository.save(user);
    }

    public UserManagement getUser(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public UserManagement getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    public Page<UserManagement> getAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void deleteUser(String id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public Page<UserManagement> getUsersByDepartment(String department, Pageable pageable) {
        return repository.findByDepartment(department, pageable);
    }

    public Page<UserManagement> getUsersByRole(UserManagement.UserRole role, Pageable pageable) {
        return repository.findByRole(role, pageable);
    }

    public Page<UserManagement> getUsersByStatus(UserManagement.UserStatus status, Pageable pageable) {
        return repository.findByStatus(status, pageable);
    }

    public Page<UserManagement> getUsersBySkillLevel(UserManagement.SkillLevel skillLevel, Pageable pageable) {
        return repository.findBySkillLevel(skillLevel, pageable);
    }
} 