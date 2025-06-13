package com.example.demo.service;

import com.example.demo.dto.AdvancedSettingsDTO;
import com.example.demo.entity.AdvancedSettings;
import com.example.demo.repository.AdvancedSettingsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdvancedSettingsService {

    private final AdvancedSettingsRepository advancedSettingsRepository;

    public AdvancedSettingsDTO createSettings(AdvancedSettingsDTO settingsDTO) {
        AdvancedSettings settings = new AdvancedSettings();
        updateSettingsFromDTO(settings, settingsDTO);
        AdvancedSettings savedSettings = advancedSettingsRepository.save(settings);
        return convertToDTO(savedSettings);
    }

    @Transactional(readOnly = true)
    public AdvancedSettingsDTO getSettings(UUID id) {
        AdvancedSettings settings = advancedSettingsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Advanced settings not found with id: " + id));
        return convertToDTO(settings);
    }

    @Transactional(readOnly = true)
    public List<AdvancedSettingsDTO> getAllSettings() {
        return advancedSettingsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AdvancedSettingsDTO updateSettings(UUID id, AdvancedSettingsDTO settingsDTO) {
        AdvancedSettings settings = advancedSettingsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Advanced settings not found with id: " + id));

        updateSettingsFromDTO(settings, settingsDTO);
        AdvancedSettings updatedSettings = advancedSettingsRepository.save(settings);
        return convertToDTO(updatedSettings);
    }

    public void deleteSettings(UUID id) {
        if (!advancedSettingsRepository.existsById(id)) {
            throw new EntityNotFoundException("Advanced settings not found with id: " + id);
        }
        advancedSettingsRepository.deleteById(id);
    }

    private AdvancedSettingsDTO convertToDTO(AdvancedSettings settings) {
        AdvancedSettingsDTO dto = new AdvancedSettingsDTO();
        dto.setId(settings.getId());
        dto.setSessionTimeout(settings.getSessionTimeout());
        dto.setMaxFileSize(settings.getMaxFileSize());
        dto.setEnableDebugMode(settings.getEnableDebugMode());
        dto.setAutoBackup(settings.getAutoBackup());
        dto.setMaintenanceMode(settings.getMaintenanceMode());
        return dto;
    }

    private void updateSettingsFromDTO(AdvancedSettings settings, AdvancedSettingsDTO dto) {
        settings.setSessionTimeout(dto.getSessionTimeout());
        settings.setMaxFileSize(dto.getMaxFileSize());
        settings.setEnableDebugMode(dto.getEnableDebugMode());
        settings.setAutoBackup(dto.getAutoBackup());
        settings.setMaintenanceMode(dto.getMaintenanceMode());
    }
} 