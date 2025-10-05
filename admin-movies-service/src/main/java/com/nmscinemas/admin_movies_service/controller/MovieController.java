package com.nmscinemas.admin_movies_service.controller;

import com.nmscinemas.admin_movies_service.dto.Movie;
import com.nmscinemas.admin_movies_service.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Simplified Movie Creation Service REST Controller
 * - Focuses on creating Movies for administration
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService service;

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    // Constructor-based dependency injection: Spring injects MovieService here
    @Autowired
    public MovieController(MovieService service){
        this.service = service;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return service.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getMovieById(id));
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(service.createMovie(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(service.updateMovie(movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        System.out.println("Calling delete movie for id " + id);
        service.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "No file uploaded"));
        }

        System.out.println("Calling upload file not empty...  " + uploadDir);

        // Ensure folder exists
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        System.out.println("Upload path  " + uploadPath);

        // Sanitize & uniquify filename
        String original = StringUtils.cleanPath(file.getOriginalFilename() == null ? "file" : file.getOriginalFilename());
        String ext = "";
        int dot = original.lastIndexOf('.');
        if (dot >= 0) ext = original.substring(dot);
        String base = (dot >= 0 ? original.substring(0, dot) : original).replaceAll("[^a-zA-Z0-9-_]", "_");
        String storedName = base + "_" + Instant.now().toEpochMilli() + ext;

        // Save file
        Path target = uploadPath.resolve(storedName);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        // Build public URL (served by ResourceHandler below)
        String url = "/uploads/" + storedName;

        return ResponseEntity.ok(Map.of(
                "fileName", storedName,
                "url", url
        ));
    }

    /*
     * Health Check Endpoint
     * - Simple endpoint to verify service is running
     * - Important for microservices deployment and orchestration
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Movie Creation Service is running!");
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
        errorResponse.put("error", "Movie creation processing failed");
        errorResponse.put("message", "Unable to process movie creation at this time. Please try again later.");
        errorResponse.put("timestamp", java.time.Instant.now().toString());
        errorResponse.put("internalErrorMessage", e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

}
