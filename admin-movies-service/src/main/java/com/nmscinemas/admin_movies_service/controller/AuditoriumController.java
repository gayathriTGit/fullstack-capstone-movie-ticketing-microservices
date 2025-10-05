package com.nmscinemas.admin_movies_service.controller;

import com.nmscinemas.admin_movies_service.dto.Auditorium;
import com.nmscinemas.admin_movies_service.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Simplified Auditorium Creation Service REST Controller
 * - Focuses on creating Movies for administration
 */
@RestController
@RequestMapping("/api/auditoriums")
public class AuditoriumController {

    private final AuditoriumService service;

    @Autowired
    public AuditoriumController(AuditoriumService service){ this.service = service;}

    @GetMapping
    public List<Auditorium> getAllAuditoriums() { return service.getAllAuditoriums(); }

    @GetMapping("/{id}")
    public ResponseEntity<Auditorium> getAuditoriumById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAuditoriumById(id));
    }

    @PostMapping
    public ResponseEntity<Auditorium> createAuditorium(@RequestBody Auditorium theater) {
        return ResponseEntity.ok(service.createAuditorium(theater));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auditorium> updateAuditorium(@RequestBody Auditorium theater) {
        return ResponseEntity.ok(service.updateAuditorium(theater));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuditorium(@PathVariable Long id) {
        System.out.println("Calling delete auditorium for id " + id);
        service.deleteAuditorium(id);
        return ResponseEntity.noContent().build();
    }

    /*
     * Health Check Endpoint
     * - Simple endpoint to verify service is running
     * - Important for microservices deployment and orchestration
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Auditorium Creation Service is running!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, String> fieldErrors = new HashMap<>();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        errorResponse.put("error", "Validation failed");
        errorResponse.put("message", "Please check the required fields");
        errorResponse.put("fieldErrors", fieldErrors);
        errorResponse.put("timestamp", java.time.Instant.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Auditorium creation processing failed");
        errorResponse.put("message", "Unable to process auditorium creation at this time. Please try again later.");
        errorResponse.put("timestamp", java.time.Instant.now().toString());
        errorResponse.put("internalErrorMessage", e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

}
