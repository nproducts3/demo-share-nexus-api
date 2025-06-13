package com.example.demo.controller;

import com.example.demo.dto.NotificationSettingsDTO;
import com.example.demo.service.NotificationSettingsService;
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
@RequestMapping("/api/notification-settings")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Tag(name = "Notification Settings", description = "Notification Settings management APIs")
public class NotificationSettingsController {

    private final NotificationSettingsService notificationSettingsService;

    @PostMapping
    @Operation(summary = "Create new notification settings", description = "Creates new notification settings with the provided configuration")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Settings created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<NotificationSettingsDTO> createSettings(
            @Valid @RequestBody NotificationSettingsDTO settingsDTO) {
        return new ResponseEntity<>(notificationSettingsService.createSettings(settingsDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get notification settings by ID", description = "Retrieves notification settings by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Settings found"),
        @ApiResponse(responseCode = "404", description = "Settings not found")
    })
    public ResponseEntity<NotificationSettingsDTO> getSettings(
            @Parameter(description = "UUID of the settings to retrieve") @PathVariable UUID id) {
        return ResponseEntity.ok(notificationSettingsService.getSettings(id));
    }

    @GetMapping
    @Operation(summary = "Get all notification settings", description = "Retrieves all notification settings")
    @ApiResponse(responseCode = "200", description = "List of all notification settings")
    public ResponseEntity<List<NotificationSettingsDTO>> getAllSettings() {
        return ResponseEntity.ok(notificationSettingsService.getAllSettings());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update notification settings", description = "Updates existing notification settings by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Settings updated successfully"),
        @ApiResponse(responseCode = "404", description = "Settings not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<NotificationSettingsDTO> updateSettings(
            @Parameter(description = "UUID of the settings to update") @PathVariable UUID id,
            @Valid @RequestBody NotificationSettingsDTO settingsDTO) {
        return ResponseEntity.ok(notificationSettingsService.updateSettings(id, settingsDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete notification settings", description = "Deletes notification settings by its UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Settings deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Settings not found")
    })
    public ResponseEntity<Void> deleteSettings(
            @Parameter(description = "UUID of the settings to delete") @PathVariable UUID id) {
        notificationSettingsService.deleteSettings(id);
        return ResponseEntity.noContent().build();
    }
} 