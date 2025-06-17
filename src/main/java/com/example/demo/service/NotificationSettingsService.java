package com.example.demo.service;

import com.example.demo.dto.NotificationSettingsDTO;
import com.example.demo.entity.NotificationSettings;
import com.example.demo.repository.NotificationSettingsRepository;
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
public class NotificationSettingsService {

    private final NotificationSettingsRepository notificationSettingsRepository;

    public NotificationSettingsDTO createSettings(NotificationSettingsDTO settingsDTO) {
        NotificationSettings settings = new NotificationSettings();
        settings.setId(UUID.randomUUID().toString());
        updateSettingsFromDTO(settings, settingsDTO);
        NotificationSettings savedSettings = notificationSettingsRepository.save(settings);
        return convertToDTO(savedSettings);
    }

    @Transactional(readOnly = true)
    public NotificationSettingsDTO getSettings(String id) {
        NotificationSettings settings = notificationSettingsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification settings not found with id: " + id));
        return convertToDTO(settings);
    }

    @Transactional(readOnly = true)
    public List<NotificationSettingsDTO> getAllSettings() {
        return notificationSettingsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NotificationSettingsDTO updateSettings(String id, NotificationSettingsDTO settingsDTO) {
        NotificationSettings settings = notificationSettingsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification settings not found with id: " + id));

        updateSettingsFromDTO(settings, settingsDTO);
        NotificationSettings updatedSettings = notificationSettingsRepository.save(settings);
        return convertToDTO(updatedSettings);
    }

    public void deleteSettings(String id) {
        if (!notificationSettingsRepository.existsById(id)) {
            throw new EntityNotFoundException("Notification settings not found with id: " + id);
        }
        notificationSettingsRepository.deleteById(id);
    }

    private NotificationSettingsDTO convertToDTO(NotificationSettings settings) {
        NotificationSettingsDTO dto = new NotificationSettingsDTO();
        dto.setId(settings.getId());
        dto.setEmailNotifications(settings.getEmailNotifications());
        dto.setPushNotifications(settings.getPushNotifications());
        dto.setSessionReminders(settings.getSessionReminders());
        dto.setWeeklyReports(settings.getWeeklyReports());
        dto.setMarketingEmails(settings.getMarketingEmails());
        return dto;
    }

    private void updateSettingsFromDTO(NotificationSettings settings, NotificationSettingsDTO dto) {
        settings.setEmailNotifications(dto.getEmailNotifications());
        settings.setPushNotifications(dto.getPushNotifications());
        settings.setSessionReminders(dto.getSessionReminders());
        settings.setWeeklyReports(dto.getWeeklyReports());
        settings.setMarketingEmails(dto.getMarketingEmails());
    }
} 