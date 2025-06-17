package com.example.demo.service;

import com.example.demo.dto.SettingsProfileDTO;
import com.example.demo.entity.SettingsProfile;
import com.example.demo.repository.SettingsProfileRepository;
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
public class SettingsProfileService {

    private final SettingsProfileRepository settingsProfileRepository;

    public SettingsProfileDTO createProfile(SettingsProfileDTO profileDTO) {
        if (settingsProfileRepository.existsByEmail(profileDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        SettingsProfile profile = new SettingsProfile();
        profile.setId(UUID.randomUUID().toString());
        updateProfileFromDTO(profile, profileDTO);
        SettingsProfile savedProfile = settingsProfileRepository.save(profile);
        return convertToDTO(savedProfile);
    }

    @Transactional(readOnly = true)
    public SettingsProfileDTO getProfile(String id) {
        SettingsProfile profile = settingsProfileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + id));
        return convertToDTO(profile);
    }

    @Transactional(readOnly = true)
    public List<SettingsProfileDTO> getAllProfiles() {
        return settingsProfileRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SettingsProfileDTO updateProfile(String id, SettingsProfileDTO profileDTO) {
        SettingsProfile profile = settingsProfileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + id));

        if (!profile.getEmail().equals(profileDTO.getEmail()) && 
            settingsProfileRepository.existsByEmail(profileDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        updateProfileFromDTO(profile, profileDTO);
        SettingsProfile updatedProfile = settingsProfileRepository.save(profile);
        return convertToDTO(updatedProfile);
    }

    public void deleteProfile(String id) {
        if (!settingsProfileRepository.existsById(id)) {
            throw new EntityNotFoundException("Profile not found with id: " + id);
        }
        settingsProfileRepository.deleteById(id);
    }

    private SettingsProfileDTO convertToDTO(SettingsProfile profile) {
        SettingsProfileDTO dto = new SettingsProfileDTO();
        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setEmail(profile.getEmail());
        dto.setPhone(profile.getPhone());
        dto.setDepartment(profile.getDepartment());
        dto.setCompany(profile.getCompany());
        dto.setBio(profile.getBio());
        return dto;
    }

    private void updateProfileFromDTO(SettingsProfile profile, SettingsProfileDTO dto) {
        profile.setName(dto.getName());
        profile.setEmail(dto.getEmail());
        profile.setPhone(dto.getPhone());
        profile.setDepartment(dto.getDepartment());
        profile.setCompany(dto.getCompany());
        profile.setBio(dto.getBio());
    }
} 