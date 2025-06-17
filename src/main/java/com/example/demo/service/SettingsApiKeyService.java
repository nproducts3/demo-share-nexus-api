package com.example.demo.service;

import com.example.demo.dto.SettingsApiKeyDTO;
import com.example.demo.entity.SettingsApiKey;
import com.example.demo.repository.SettingsApiKeyRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SettingsApiKeyService {

    @Autowired
    private SettingsApiKeyRepository repository;

    public List<SettingsApiKeyDTO> getAllApiKeys() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SettingsApiKeyDTO getApiKeyById(String id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ValidationException("API Key not found"));
    }

    @Transactional
    public SettingsApiKeyDTO createApiKey(SettingsApiKeyDTO dto) {
        if (repository.existsByKey(dto.getKey())) {
            throw new ValidationException("API Key already exists");
        }

        SettingsApiKey entity = new SettingsApiKey();
        entity.setId(UUID.randomUUID().toString());
        entity.setName(dto.getName());
        entity.setKey(dto.getKey());
        
        // Set dates if provided, otherwise they will be set by @PrePersist
        if (dto.getCreatedAt() != null) {
            entity.setCreatedAt(dto.getCreatedAt());
        }
        if (dto.getLastUsed() != null) {
            entity.setLastUsed(dto.getLastUsed());
        }

        return convertToDTO(repository.save(entity));
    }

    @Transactional
    public SettingsApiKeyDTO updateApiKey(String id, SettingsApiKeyDTO dto) {
        SettingsApiKey existing = repository.findById(id)
                .orElseThrow(() -> new ValidationException("API Key not found"));
        
        // Check if the new key already exists for a different record
        if (!existing.getKey().equals(dto.getKey()) && repository.existsByKey(dto.getKey())) {
            throw new ValidationException("API Key already exists");
        }

        // Update the fields
        existing.setName(dto.getName());
        existing.setKey(dto.getKey());
        // Keep the original creation date
        // lastUsed will be automatically updated by @PreUpdate
        
        return convertToDTO(repository.save(existing));
    }

    @Transactional
    public void deleteApiKey(String id) {
        if (!repository.existsById(id)) {
            throw new ValidationException("API Key not found");
        }
        repository.deleteById(id);
    }

    private SettingsApiKeyDTO convertToDTO(SettingsApiKey entity) {
        SettingsApiKeyDTO dto = new SettingsApiKeyDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setKey(entity.getKey());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setLastUsed(entity.getLastUsed());
        return dto;
    }
} 