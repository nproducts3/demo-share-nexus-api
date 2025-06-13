package com.example.demo.controller;

import com.example.demo.dto.AdvancedSettingsDTO;
import com.example.demo.service.AdvancedSettingsService;
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
@RequestMapping("/api/advanced-settings")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Tag(name = "Advanced Settings", description = "Advanced Settings management APIs")
public class AdvancedSettingsController {

    private final AdvancedSettingsService advancedSettingsService;

    @PostMapping
    @Operation(summary = "Create new advanced settings", description = "Creates new advanced settings with the provided configuration")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Settings created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<AdvancedSettingsDTO> createSettings(
            @Valid @RequestBody AdvancedSettingsDTO settingsDTO) {
        return new ResponseEntity<>(advancedSettingsService.createSettings(settingsDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get advanced settings by ID", description = "Retrieves advanced settings by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Settings found"),
        @ApiResponse(responseCode = "404", description = "Settings not found")
    })
    public ResponseEntity<AdvancedSettingsDTO> getSettings(
            @Parameter(description = "UUID of the settings to retrieve") @PathVariable UUID id) {
        return ResponseEntity.ok(advancedSettingsService.getSettings(id));
    }

    @GetMapping
    @Operation(summary = "Get all advanced settings", description = "Retrieves all advanced settings")
    @ApiResponse(responseCode = "200", description = "List of all advanced settings")
    public ResponseEntity<List<AdvancedSettingsDTO>> getAllSettings() {
        return ResponseEntity.ok(advancedSettingsService.getAllSettings());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update advanced settings", description = "Updates existing advanced settings by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Settings updated successfully"),
        @ApiResponse(responseCode = "404", description = "Settings not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<AdvancedSettingsDTO> updateSettings(
            @Parameter(description = "UUID of the settings to update") @PathVariable UUID id,
            @Valid @RequestBody AdvancedSettingsDTO settingsDTO) {
        return ResponseEntity.ok(advancedSettingsService.updateSettings(id, settingsDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete advanced settings", description = "Deletes advanced settings by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Settings deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Settings not found")
    })
    public ResponseEntity<Void> deleteSettings(
            @Parameter(description = "UUID of the settings to delete") @PathVariable UUID id) {
        advancedSettingsService.deleteSettings(id);
        return ResponseEntity.noContent().build();
    }
} 