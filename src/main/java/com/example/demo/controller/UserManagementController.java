package com.example.demo.controller;

import com.example.demo.entity.UserManagement;
import com.example.demo.dto.UserManagementDTO;
import com.example.demo.dto.PaginatedResponse;
import com.example.demo.service.UserManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "User Management", description = "User management APIs")
public class UserManagementController {
    private final UserManagementService service;

    public UserManagementController(UserManagementService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
        summary = "Create a new user",
        description = "Creates a new user with the provided details"
    )
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "409", description = "Email already exists")
    public ResponseEntity<UserManagement> createUser(@Valid @RequestBody UserManagementDTO dto) {
        try {
            return new ResponseEntity<>(service.createUser(dto), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update an existing user",
        description = "Updates the user with the specified ID"
    )
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "409", description = "Email already exists")
    public ResponseEntity<UserManagement> updateUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable String id,
            @Valid @RequestBody UserManagementDTO dto) {
        return ResponseEntity.ok(service.updateUser(id, dto));
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get a user by ID",
        description = "Retrieves the user with the specified ID"
    )
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserManagement> getUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable String id) {
        return ResponseEntity.ok(service.getUser(id));
    }

    @GetMapping("/email/{email}")
    @Operation(
        summary = "Get a user by email",
        description = "Retrieves the user with the specified email"
    )
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserManagement> getUserByEmail(
            @Parameter(description = "User email", required = true)
            @PathVariable String email) {
        return ResponseEntity.ok(service.getUserByEmail(email));
    }

    @GetMapping
    @Operation(
        summary = "Get all users with pagination",
        description = "Retrieves all users with pagination"
    )
    @ApiResponse(responseCode = "200", description = "List of all users")
    public ResponseEntity<PaginatedResponse<UserManagement>> getAllUsers(
            @Parameter(description = "Page number (1-based)")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Items per page")
            @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("name").ascending());
        return ResponseEntity.ok(PaginatedResponse.from(service.getAllUsers(pageable)));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a user",
        description = "Deletes the user with the specified ID"
    )
    @ApiResponse(responseCode = "204", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable String id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/department/{department}")
    @Operation(
        summary = "Get users by department with pagination",
        description = "Retrieves all users in the specified department with pagination"
    )
    @ApiResponse(responseCode = "200", description = "List of users in the department")
    public ResponseEntity<PaginatedResponse<UserManagement>> getUsersByDepartment(
            @Parameter(description = "Department name", required = true)
            @PathVariable String department,
            @Parameter(description = "Page number (1-based)")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Items per page")
            @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("name").ascending());
        return ResponseEntity.ok(PaginatedResponse.from(service.getUsersByDepartment(department, pageable)));
    }

    @GetMapping("/role/{role}")
    @Operation(
        summary = "Get users by role with pagination",
        description = "Retrieves all users with the specified role with pagination"
    )
    @ApiResponse(responseCode = "200", description = "List of users with the role")
    public ResponseEntity<PaginatedResponse<UserManagement>> getUsersByRole(
            @Parameter(description = "User role", required = true)
            @PathVariable UserManagement.UserRole role,
            @Parameter(description = "Page number (1-based)")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Items per page")
            @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("name").ascending());
        return ResponseEntity.ok(PaginatedResponse.from(service.getUsersByRole(role, pageable)));
    }

    @GetMapping("/status/{status}")
    @Operation(
        summary = "Get users by status with pagination",
        description = "Retrieves all users with the specified status with pagination"
    )
    @ApiResponse(responseCode = "200", description = "List of users with the status")
    public ResponseEntity<PaginatedResponse<UserManagement>> getUsersByStatus(
            @Parameter(description = "User status", required = true)
            @PathVariable UserManagement.UserStatus status,
            @Parameter(description = "Page number (1-based)")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Items per page")
            @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("name").ascending());
        return ResponseEntity.ok(PaginatedResponse.from(service.getUsersByStatus(status, pageable)));
    }

    @GetMapping("/skill-level/{skillLevel}")
    @Operation(
        summary = "Get users by skill level with pagination",
        description = "Retrieves all users with the specified skill level with pagination"
    )
    @ApiResponse(responseCode = "200", description = "List of users with the skill level")
    public ResponseEntity<PaginatedResponse<UserManagement>> getUsersBySkillLevel(
            @Parameter(description = "Skill level", required = true)
            @PathVariable UserManagement.SkillLevel skillLevel,
            @Parameter(description = "Page number (1-based)")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Items per page")
            @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("name").ascending());
        return ResponseEntity.ok(PaginatedResponse.from(service.getUsersBySkillLevel(skillLevel, pageable)));
    }
} 