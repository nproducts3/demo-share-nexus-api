package com.example.demo.controller;

import com.example.demo.dto.SettingsTeamDTO;
import com.example.demo.service.SettingsTeamService;
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
@RequestMapping("/api/settings/team")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Settings Team", description = "API for managing team settings")
public class SettingsTeamController {

    private final SettingsTeamService service;

    public SettingsTeamController(SettingsTeamService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create team settings", description = "Creates new team settings")
    @ApiResponse(responseCode = "200", description = "Team settings created successfully")
    public ResponseEntity<SettingsTeamDTO> createSettings(@Valid @RequestBody SettingsTeamDTO settingsDTO) {
        return ResponseEntity.ok(service.createSettings(settingsDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get team settings by ID", description = "Retrieves team settings by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Team settings found"),
        @ApiResponse(responseCode = "404", description = "Team settings not found")
    })
    public ResponseEntity<SettingsTeamDTO> getSettings(
            @Parameter(description = "ID of the settings to retrieve") @PathVariable String id) {
        return ResponseEntity.ok(service.getSettings(id));
    }

    @GetMapping
    @Operation(summary = "Get all team settings", description = "Retrieves all team settings")
    @ApiResponse(responseCode = "200", description = "All team settings retrieved successfully")
    public ResponseEntity<List<SettingsTeamDTO>> getAllSettings() {
        return ResponseEntity.ok(service.getAllSettings());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update team settings", description = "Updates existing team settings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Team settings updated successfully"),
        @ApiResponse(responseCode = "404", description = "Team settings not found")
    })
    public ResponseEntity<SettingsTeamDTO> updateSettings(
            @Parameter(description = "ID of the settings to update") @PathVariable String id,
            @Valid @RequestBody SettingsTeamDTO settingsDTO) {
        return ResponseEntity.ok(service.updateSettings(id, settingsDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete team settings", description = "Deletes team settings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Team settings deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Team settings not found")
    })
    public ResponseEntity<Void> deleteSettings(
            @Parameter(description = "ID of the settings to delete") @PathVariable String id) {
        service.deleteSettings(id);
        return ResponseEntity.ok().build();
    }
} 