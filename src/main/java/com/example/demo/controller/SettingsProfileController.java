package com.example.demo.controller;

import com.example.demo.dto.SettingsProfileDTO;
import com.example.demo.service.SettingsProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/settings-profiles")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Tag(name = "Settings Profile", description = "Settings Profile management APIs")
public class SettingsProfileController {

    private final SettingsProfileService settingsProfileService;

    @PostMapping
    @Operation(summary = "Create a new settings profile", description = "Creates a new settings profile with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Profile created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input or email already exists")
    })
    public ResponseEntity<SettingsProfileDTO> createProfile(
            @Valid @RequestBody SettingsProfileDTO profileDTO) {
        return new ResponseEntity<>(settingsProfileService.createProfile(profileDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a settings profile by ID", description = "Retrieves a settings profile by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile found"),
        @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<SettingsProfileDTO> getProfile(
            @Parameter(description = "UUID of the profile to retrieve") @PathVariable UUID id) {
        return ResponseEntity.ok(settingsProfileService.getProfile(id));
    }

    @GetMapping
    @Operation(summary = "Get all settings profiles", description = "Retrieves all settings profiles")
    @ApiResponse(responseCode = "200", description = "List of all profiles")
    public ResponseEntity<List<SettingsProfileDTO>> getAllProfiles() {
        return ResponseEntity.ok(settingsProfileService.getAllProfiles());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a settings profile", description = "Updates an existing settings profile by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile updated successfully"),
        @ApiResponse(responseCode = "404", description = "Profile not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input or email already exists")
    })
    public ResponseEntity<SettingsProfileDTO> updateProfile(
            @Parameter(description = "UUID of the profile to update") @PathVariable UUID id,
            @Valid @RequestBody SettingsProfileDTO profileDTO) {
        return ResponseEntity.ok(settingsProfileService.updateProfile(id, profileDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a settings profile", description = "Deletes a settings profile by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Profile deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<Void> deleteProfile(
            @Parameter(description = "UUID of the profile to delete") @PathVariable UUID id) {
        settingsProfileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
} 