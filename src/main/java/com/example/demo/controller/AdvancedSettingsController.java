package com.example.demo.controller;

import com.example.demo.dto.AdvancedSettingsDTO;
import com.example.demo.service.AdvancedSettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advanced-settings")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Advanced Settings", description = "Advanced Settings management APIs")
public class AdvancedSettingsController {
    private final AdvancedSettingsService service;

    public AdvancedSettingsController(AdvancedSettingsService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
        summary = "Create new advanced settings",
        description = "Creates new advanced settings with the provided configuration"
    )
    @ApiResponse(responseCode = "201", description = "Settings created successfully")
    public ResponseEntity<AdvancedSettingsDTO> createSettings(@Valid @RequestBody AdvancedSettingsDTO dto) {
        return new ResponseEntity<>(service.createSettings(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update existing advanced settings",
        description = "Updates the advanced settings with the specified ID"
    )
    @ApiResponse(responseCode = "200", description = "Settings updated successfully")
    public ResponseEntity<AdvancedSettingsDTO> updateSettings(
            @Parameter(description = "Settings ID", required = true)
            @PathVariable String id,
            @Valid @RequestBody AdvancedSettingsDTO dto) {
        return ResponseEntity.ok(service.updateSettings(id, dto));
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get advanced settings by ID",
        description = "Retrieves the advanced settings with the specified ID"
    )
    @ApiResponse(responseCode = "200", description = "Settings found")
    @ApiResponse(responseCode = "404", description = "Settings not found")
    public ResponseEntity<AdvancedSettingsDTO> getSettings(
            @Parameter(description = "Settings ID", required = true)
            @PathVariable String id) {
        return ResponseEntity.ok(service.getSettingsById(id));
    }

    @GetMapping
    @Operation(
        summary = "Get all advanced settings",
        description = "Retrieves all advanced settings"
    )
    @ApiResponse(responseCode = "200", description = "List of all advanced settings")
    public ResponseEntity<List<AdvancedSettingsDTO>> getAllSettings() {
        return ResponseEntity.ok(service.getAllSettings());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete advanced settings",
        description = "Deletes the advanced settings with the specified ID"
    )
    @ApiResponse(responseCode = "204", description = "Settings deleted successfully")
    public ResponseEntity<Void> deleteSettings(
            @Parameter(description = "Settings ID", required = true)
            @PathVariable String id) {
        service.deleteSettings(id);
        return ResponseEntity.noContent().build();
    }
} 