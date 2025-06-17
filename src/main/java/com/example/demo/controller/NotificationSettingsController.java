package com.example.demo.controller;

import com.example.demo.dto.NotificationSettingsDTO;
import com.example.demo.service.NotificationSettingsService;
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
@RequestMapping("/api/settings/notifications")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Notification Settings", description = "API for managing notification settings")
public class NotificationSettingsController {

    private final NotificationSettingsService service;

    public NotificationSettingsController(NotificationSettingsService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create notification settings", description = "Creates new notification settings")
    @ApiResponse(responseCode = "200", description = "Notification settings created successfully")
    public ResponseEntity<NotificationSettingsDTO> createSettings(@Valid @RequestBody NotificationSettingsDTO settingsDTO) {
        return ResponseEntity.ok(service.createSettings(settingsDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get notification settings by ID", description = "Retrieves notification settings by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notification settings found"),
        @ApiResponse(responseCode = "404", description = "Notification settings not found")
    })
    public ResponseEntity<NotificationSettingsDTO> getSettings(
            @Parameter(description = "ID of the settings to retrieve") @PathVariable String id) {
        return ResponseEntity.ok(service.getSettings(id));
    }

    @GetMapping
    @Operation(summary = "Get all notification settings", description = "Retrieves all notification settings")
    @ApiResponse(responseCode = "200", description = "All notification settings retrieved successfully")
    public ResponseEntity<List<NotificationSettingsDTO>> getAllSettings() {
        return ResponseEntity.ok(service.getAllSettings());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update notification settings", description = "Updates existing notification settings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notification settings updated successfully"),
        @ApiResponse(responseCode = "404", description = "Notification settings not found")
    })
    public ResponseEntity<NotificationSettingsDTO> updateSettings(
            @Parameter(description = "ID of the settings to update") @PathVariable String id,
            @Valid @RequestBody NotificationSettingsDTO settingsDTO) {
        return ResponseEntity.ok(service.updateSettings(id, settingsDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete notification settings", description = "Deletes notification settings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notification settings deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Notification settings not found")
    })
    public ResponseEntity<Void> deleteSettings(
            @Parameter(description = "ID of the settings to delete") @PathVariable String id) {
        service.deleteSettings(id);
        return ResponseEntity.ok().build();
    }
} 