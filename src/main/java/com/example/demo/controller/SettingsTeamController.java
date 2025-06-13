package com.example.demo.controller;

import com.example.demo.dto.SettingsTeamDTO;
import com.example.demo.service.SettingsTeamService;
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
@RequestMapping("/api/settings-team")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Tag(name = "Team Settings", description = "Team Settings management APIs")
public class SettingsTeamController {

    private final SettingsTeamService settingsTeamService;

    @PostMapping
    @Operation(summary = "Create new team settings", description = "Creates new team settings with the provided configuration")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Settings created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<SettingsTeamDTO> createSettings(
            @Valid @RequestBody SettingsTeamDTO settingsDTO) {
        return new ResponseEntity<>(settingsTeamService.createSettings(settingsDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get team settings by ID", description = "Retrieves team settings by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Settings found"),
        @ApiResponse(responseCode = "404", description = "Settings not found")
    })
    public ResponseEntity<SettingsTeamDTO> getSettings(
            @Parameter(description = "UUID of the settings to retrieve") @PathVariable UUID id) {
        return ResponseEntity.ok(settingsTeamService.getSettings(id));
    }

    @GetMapping
    @Operation(summary = "Get all team settings", description = "Retrieves all team settings")
    @ApiResponse(responseCode = "200", description = "List of all team settings")
    public ResponseEntity<List<SettingsTeamDTO>> getAllSettings() {
        return ResponseEntity.ok(settingsTeamService.getAllSettings());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update team settings", description = "Updates existing team settings by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Settings updated successfully"),
        @ApiResponse(responseCode = "404", description = "Settings not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<SettingsTeamDTO> updateSettings(
            @Parameter(description = "UUID of the settings to update") @PathVariable UUID id,
            @Valid @RequestBody SettingsTeamDTO settingsDTO) {
        return ResponseEntity.ok(settingsTeamService.updateSettings(id, settingsDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete team settings", description = "Deletes team settings by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Settings deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Settings not found")
    })
    public ResponseEntity<Void> deleteSettings(
            @Parameter(description = "UUID of the settings to delete") @PathVariable UUID id) {
        settingsTeamService.deleteSettings(id);
        return ResponseEntity.noContent().build();
    }
} 