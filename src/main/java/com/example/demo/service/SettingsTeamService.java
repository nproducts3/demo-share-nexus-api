package com.example.demo.service;

import com.example.demo.dto.SettingsTeamDTO;
import com.example.demo.entity.SettingsTeam;
import com.example.demo.repository.SettingsTeamRepository;
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
public class SettingsTeamService {

    private final SettingsTeamRepository settingsTeamRepository;

    public SettingsTeamDTO createSettings(SettingsTeamDTO settingsDTO) {
        SettingsTeam settings = new SettingsTeam();
        updateSettingsFromDTO(settings, settingsDTO);
        SettingsTeam savedSettings = settingsTeamRepository.save(settings);
        return convertToDTO(savedSettings);
    }

    @Transactional(readOnly = true)
    public SettingsTeamDTO getSettings(UUID id) {
        SettingsTeam settings = settingsTeamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team settings not found with id: " + id));
        return convertToDTO(settings);
    }

    @Transactional(readOnly = true)
    public List<SettingsTeamDTO> getAllSettings() {
        return settingsTeamRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SettingsTeamDTO updateSettings(UUID id, SettingsTeamDTO settingsDTO) {
        SettingsTeam settings = settingsTeamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team settings not found with id: " + id));

        updateSettingsFromDTO(settings, settingsDTO);
        SettingsTeam updatedSettings = settingsTeamRepository.save(settings);
        return convertToDTO(updatedSettings);
    }

    public void deleteSettings(UUID id) {
        if (!settingsTeamRepository.existsById(id)) {
            throw new EntityNotFoundException("Team settings not found with id: " + id);
        }
        settingsTeamRepository.deleteById(id);
    }

    private SettingsTeamDTO convertToDTO(SettingsTeam settings) {
        SettingsTeamDTO dto = new SettingsTeamDTO();
        dto.setId(settings.getId());
        dto.setMaxSessionsPerDay(settings.getMaxSessionsPerDay());
        dto.setAutoApproveRegistrations(settings.getAutoApproveRegistrations());
        dto.setRequireManagerApproval(settings.getRequireManagerApproval());
        dto.setSessionReminderHours(settings.getSessionReminderHours());
        return dto;
    }

    private void updateSettingsFromDTO(SettingsTeam settings, SettingsTeamDTO dto) {
        settings.setMaxSessionsPerDay(dto.getMaxSessionsPerDay());
        settings.setAutoApproveRegistrations(dto.getAutoApproveRegistrations());
        settings.setRequireManagerApproval(dto.getRequireManagerApproval());
        settings.setSessionReminderHours(dto.getSessionReminderHours());
    }
} 