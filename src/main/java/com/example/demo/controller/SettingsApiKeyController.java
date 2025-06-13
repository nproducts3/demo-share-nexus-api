package com.example.demo.controller;

import com.example.demo.dto.SettingsApiKeyDTO;
import com.example.demo.service.SettingsApiKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/settings/api-keys")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Settings API Keys", description = "API for managing settings API keys")
public class SettingsApiKeyController {

    @Autowired
    private SettingsApiKeyService service;

    @GetMapping
    @Operation(summary = "Get all API keys", description = "Retrieves a list of all API keys")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all API keys")
    public ResponseEntity<List<SettingsApiKeyDTO>> getAllApiKeys() {
        return ResponseEntity.ok(service.getAllApiKeys());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get API key by ID", description = "Retrieves a specific API key by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the API key"),
        @ApiResponse(responseCode = "404", description = "API key not found")
    })
    public ResponseEntity<SettingsApiKeyDTO> getApiKeyById(
            @Parameter(description = "ID of the API key to retrieve") @PathVariable UUID id) {
        return ResponseEntity.ok(service.getApiKeyById(id));
    }

    @PostMapping
    @Operation(summary = "Create new API key", description = "Creates a new API key")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created the API key"),
        @ApiResponse(responseCode = "400", description = "Invalid input or API key already exists")
    })
    public ResponseEntity<SettingsApiKeyDTO> createApiKey(
            @Parameter(description = "API key details") @RequestBody SettingsApiKeyDTO apiKeyDTO) {
        return ResponseEntity.ok(service.createApiKey(apiKeyDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update API key", description = "Updates an existing API key")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated the API key"),
        @ApiResponse(responseCode = "404", description = "API key not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<SettingsApiKeyDTO> updateApiKey(
            @Parameter(description = "ID of the API key to update") @PathVariable UUID id,
            @Parameter(description = "Updated API key details") @RequestBody SettingsApiKeyDTO apiKeyDTO) {
        return ResponseEntity.ok(service.updateApiKey(id, apiKeyDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete API key", description = "Deletes an API key")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted the API key"),
        @ApiResponse(responseCode = "404", description = "API key not found")
    })
    public ResponseEntity<Void> deleteApiKey(
            @Parameter(description = "ID of the API key to delete") @PathVariable UUID id) {
        service.deleteApiKey(id);
        return ResponseEntity.ok().build();
    }
} 