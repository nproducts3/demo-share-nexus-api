package com.example.demo.controller;

import com.example.demo.entity.DemoSession;
import com.example.demo.dto.DemoSessionDTO;
import com.example.demo.dto.DemoSessionResponseDTO;
import com.example.demo.dto.PaginatedResponse;
import com.example.demo.service.DemoSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Demo Sessions", description = "Demo Sessions management APIs")
public class DemoSessionController {
    private final DemoSessionService service;
    private static final Logger logger = LoggerFactory.getLogger(DemoSessionController.class);

    public DemoSessionController(DemoSessionService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
        summary = "Create a new demo session",
        description = "Creates a new demo session with the provided details"
    )
    @ApiResponse(responseCode = "201", description = "Session created successfully")
    public ResponseEntity<DemoSessionResponseDTO> createSession(@Valid @RequestBody DemoSessionDTO dto) {
        // Log incoming data for debugging
        logger.info("Received request to create demo session: " + dto);

        try {
            // Create the session
            DemoSession createdSession = service.createSession(dto);
            
            // Convert to response DTO
            DemoSessionResponseDTO responseDTO = DemoSessionResponseDTO.fromEntity(createdSession);
            
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the error with stack trace
            logger.error("Error creating session: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update an existing demo session",
        description = "Updates the demo session with the specified ID"
    )
    @ApiResponse(responseCode = "200", description = "Session updated successfully")
    public ResponseEntity<DemoSession> updateSession(
            @Parameter(description = "Session ID", required = true)
            @PathVariable String id,
            @Valid @RequestBody DemoSessionDTO dto) {
        return ResponseEntity.ok(service.updateSession(id, dto));
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get a demo session by ID",
        description = "Retrieves the demo session with the specified ID"
    )
    @ApiResponse(responseCode = "200", description = "Session found")
    @ApiResponse(responseCode = "404", description = "Session not found")
    public ResponseEntity<DemoSessionResponseDTO> getSession(
            @Parameter(description = "Session ID", required = true)
            @PathVariable String id) {
        DemoSession session = service.getSession(id);
        DemoSessionResponseDTO responseDTO = DemoSessionResponseDTO.fromEntity(session);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    @Operation(
        summary = "Get all demo sessions with pagination",
        description = "Retrieves all demo sessions"
    )
    @ApiResponse(responseCode = "200", description = "List of all sessions")
    public ResponseEntity<PaginatedResponse<DemoSessionResponseDTO>> getAllSessions(
            @Parameter(description = "Page number (1-based)")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Items per page")
            @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("date").descending());
        var pageResult = service.getAllSessions(pageable);
        
        // Convert entities to response DTOs
        var responseDTOs = pageResult.getContent().stream()
                .map(DemoSessionResponseDTO::fromEntity)
                .toList();
        
        PaginatedResponse<DemoSessionResponseDTO> response = PaginatedResponse.from(pageResult);
        response.setContent(responseDTOs);
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a demo session",
        description = "Deletes the demo session with the specified ID"
    )
    @ApiResponse(responseCode = "204", description = "Session deleted successfully")
    public ResponseEntity<Void> deleteSession(
            @Parameter(description = "Session ID", required = true)
            @PathVariable String id) {
        service.deleteSession(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/upcoming")
    @Operation(
        summary = "Get upcoming sessions with pagination",
        description = "Retrieves all upcoming demo sessions from today onwards"
    )
    @ApiResponse(responseCode = "200", description = "List of upcoming sessions")
    public ResponseEntity<PaginatedResponse<DemoSession>> getUpcomingSessions(
            @Parameter(description = "Page number (1-based)")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Items per page")
            @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("date").ascending());
        return ResponseEntity.ok(PaginatedResponse.from(service.getUpcomingSessions(pageable)));
    }

    @GetMapping("/technology/{technology}")
    @Operation(
        summary = "Get sessions by technology with pagination",
        description = "Retrieves all demo sessions for a specific technology"
    )
    @ApiResponse(responseCode = "200", description = "List of sessions for the specified technology")
    public ResponseEntity<PaginatedResponse<DemoSession>> getSessionsByTechnology(
            @Parameter(description = "Technology name", required = true)
            @PathVariable String technology,
            @Parameter(description = "Page number (1-based)")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Items per page")
            @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("date").descending());
        return ResponseEntity.ok(PaginatedResponse.from(service.getSessionsByTechnology(technology, pageable)));
    }

    @GetMapping("/difficulty/{difficulty}")
    @Operation(
        summary = "Get sessions by difficulty level with pagination",
        description = "Retrieves all demo sessions for a specific difficulty level"
    )
    @ApiResponse(responseCode = "200", description = "List of sessions for the specified difficulty")
    public ResponseEntity<PaginatedResponse<DemoSession>> getSessionsByDifficulty(
            @Parameter(description = "Difficulty level", required = true)
            @PathVariable DemoSession.DifficultyLevel difficulty,
            @Parameter(description = "Page number (1-based)")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Items per page")
            @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("date").descending());
        return ResponseEntity.ok(PaginatedResponse.from(service.getSessionsByDifficulty(difficulty, pageable)));
    }
}
