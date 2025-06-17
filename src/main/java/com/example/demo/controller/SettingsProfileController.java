package com.example.demo.controller;

import com.example.demo.dto.SettingsProfileDTO;
import com.example.demo.service.SettingsProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settings/profiles")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Settings Profile", description = "API for managing profile settings")
public class SettingsProfileController {

    private final SettingsProfileService service;

    public SettingsProfileController(SettingsProfileService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create profile settings", description = "Creates new profile settings")
    @ApiResponse(responseCode = "200", description = "Profile settings created successfully")
    public ResponseEntity<SettingsProfileDTO> createProfile(@Valid @RequestBody SettingsProfileDTO profileDTO) {
        return ResponseEntity.ok(service.createProfile(profileDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get profile settings by ID", description = "Retrieves profile settings by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile settings found"),
        @ApiResponse(responseCode = "404", description = "Profile settings not found")
    })
    public ResponseEntity<SettingsProfileDTO> getProfile(
            @Parameter(description = "ID of the profile to retrieve") @PathVariable String id) {
        return ResponseEntity.ok(service.getProfile(id));
    }

    @GetMapping
    @Operation(summary = "Get all profile settings", description = "Retrieves all profile settings")
    @ApiResponse(responseCode = "200", description = "All profile settings retrieved successfully")
    public ResponseEntity<List<SettingsProfileDTO>> getAllProfiles() {
        return ResponseEntity.ok(service.getAllProfiles());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update profile settings", description = "Updates existing profile settings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile settings updated successfully"),
        @ApiResponse(responseCode = "404", description = "Profile settings not found")
    })
    public ResponseEntity<SettingsProfileDTO> updateProfile(
            @Parameter(description = "ID of the profile to update") @PathVariable String id,
            @Valid @RequestBody SettingsProfileDTO profileDTO) {
        return ResponseEntity.ok(service.updateProfile(id, profileDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete profile settings", description = "Deletes profile settings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile settings deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Profile settings not found")
    })
    public ResponseEntity<Void> deleteProfile(
            @Parameter(description = "ID of the profile to delete") @PathVariable String id) {
        service.deleteProfile(id);
        return ResponseEntity.ok().build();
    }
} 