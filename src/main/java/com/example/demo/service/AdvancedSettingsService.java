package com.example.demo.service;

import com.example.demo.dto.AdvancedSettingsDTO;
import com.example.demo.entity.AdvancedSettings;
import com.example.demo.repository.AdvancedSettingsRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdvancedSettingsService {

    @Autowired
    private AdvancedSettingsRepository repository;

    public List<AdvancedSettingsDTO> getAllSettings() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AdvancedSettingsDTO getSettingsById(String id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ValidationException("Advanced Settings not found"));
    }

    @Transactional
    public AdvancedSettingsDTO createSettings(AdvancedSettingsDTO dto) {
        AdvancedSettings entity = new AdvancedSettings();
        entity.setId(UUID.randomUUID().toString());
        entity.setSessionTimeout(dto.getSessionTimeout());
        entity.setMaxFileSize(dto.getMaxFileSize());
        entity.setEnableDebugMode(dto.getEnableDebugMode());
        entity.setAutoBackup(dto.getAutoBackup());
        entity.setMaintenanceMode(dto.getMaintenanceMode());

        return convertToDTO(repository.save(entity));
    }

    @Transactional
    public AdvancedSettingsDTO updateSettings(String id, AdvancedSettingsDTO dto) {
        AdvancedSettings existing = repository.findById(id)
                .orElseThrow(() -> new ValidationException("Advanced Settings not found"));

        existing.setSessionTimeout(dto.getSessionTimeout());
        existing.setMaxFileSize(dto.getMaxFileSize());
        existing.setEnableDebugMode(dto.getEnableDebugMode());
        existing.setAutoBackup(dto.getAutoBackup());
        existing.setMaintenanceMode(dto.getMaintenanceMode());

        return convertToDTO(repository.save(existing));
    }

    @Transactional
    public void deleteSettings(String id) {
        if (!repository.existsById(id)) {
            throw new ValidationException("Advanced Settings not found");
        }
        repository.deleteById(id);
    }

    private AdvancedSettingsDTO convertToDTO(AdvancedSettings entity) {
        AdvancedSettingsDTO dto = new AdvancedSettingsDTO();
        dto.setId(entity.getId());
        dto.setSessionTimeout(entity.getSessionTimeout());
        dto.setMaxFileSize(entity.getMaxFileSize());
        dto.setEnableDebugMode(entity.getEnableDebugMode());
        dto.setAutoBackup(entity.getAutoBackup());
        dto.setMaintenanceMode(entity.getMaintenanceMode());
        return dto;
    }
} 