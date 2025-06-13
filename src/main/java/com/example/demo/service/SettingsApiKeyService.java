package com.example.demo.service;

import com.example.demo.dto.SettingsApiKeyDTO;
import com.example.demo.entity.SettingsApiKey;
import com.example.demo.repository.SettingsApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public SettingsApiKeyDTO getApiKeyById(UUID id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("API Key not found"));
    }

    @Transactional
    public SettingsApiKeyDTO createApiKey(SettingsApiKeyDTO dto) {
        if (repository.existsByKey(dto.getKey())) {
            throw new RuntimeException("API Key already exists");
        }
        SettingsApiKey entity = convertToEntity(dto);
        return convertToDTO(repository.save(entity));
    }

    @Transactional
    public SettingsApiKeyDTO updateApiKey(UUID id, SettingsApiKeyDTO dto) {
        SettingsApiKey existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("API Key not found"));
        
        existing.setName(dto.getName());
        existing.setKey(dto.getKey());
        return convertToDTO(repository.save(existing));
    }

    @Transactional
    public void deleteApiKey(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("API Key not found");
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

    private SettingsApiKey convertToEntity(SettingsApiKeyDTO dto) {
        SettingsApiKey entity = new SettingsApiKey();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setKey(dto.getKey());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setLastUsed(dto.getLastUsed());
        return entity;
    }
} 