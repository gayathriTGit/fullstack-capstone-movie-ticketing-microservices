package com.nmscinemas.admin_movies_service.controller;

import com.nmscinemas.admin_movies_service.dto.MovieShowTime;
import com.nmscinemas.admin_movies_service.service.MovieShowTimeService;
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
 * Simplified Movie Show Times Creation Service REST Controller
 * - Focuses on creating Movies for administration
 */
@RestController
@RequestMapping("/api/movieshowtimes")
public class MovieShowTimeController {

    private final MovieShowTimeService service;

    // Constructor-based dependency injection: Spring injects MovieService here
    @Autowired
    public MovieShowTimeController(MovieShowTimeService service){
        this.service = service;
    }

    @GetMapping
    public List<MovieShowTime> getAllMovieShowTimes() {
        return service.getAllMovieShowTimes();
    }

    @GetMapping("/{id}")
    public MovieShowTime getMovieShowTimeById(@PathVariable Long id) {
        return service.getMovieShowTimeById(id);
    }

    @GetMapping("/movies/{movieId}")
    public List<MovieShowTime> getShowTimeByMovieId(@PathVariable("movieId") Long id) {
        return service.getShowTimeByMovieId(id);
    }

//    @GetMapping("/showtimes/{movieId}")
//    public List<String> getShowTimesByMovieId(@PathVariable("movieId") Long id){
//        return service.getShowTimesByMovieId(id);
//    }

    @GetMapping("/auditoriums/{auditoriumId}")
    public List<MovieShowTime> getShowTimeByAuditoriumId(@PathVariable("auditoriumId") Long theaterId) {
        return service.getShowTimesByAuditoriumId(theaterId);
    }

    @GetMapping("/auditoriums/{auditoriumId}/movies/{movieId}")
    public List<MovieShowTime> getShowTimeByAuditoriumAndMovieId(@PathVariable("auditoriumId") Long theaterId,
                                                                 @PathVariable("movieId") Long movieId) {
        return service.getShowTimesByAuditoriumAndMovieId(theaterId, movieId);
    }

    @PostMapping
    public ResponseEntity<MovieShowTime> createMovieShowTime(@RequestBody MovieShowTime showTime) {
        System.out.println(showTime);
        return ResponseEntity.ok(service.createMovieShowTime(showTime));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieShowTime> updateMovie(@RequestBody MovieShowTime showTime) {
        return ResponseEntity.ok(service.updateMovieShowTime(showTime));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        service.deleteMovieShowTime(id);
        return ResponseEntity.noContent().build();
    }

    /*
     * Health Check Endpoint
     * - Simple endpoint to verify service is running
     * - Important for microservices deployment and orchestration
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Movie Show Times Creation Service is running!");
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
        errorResponse.put("error", "Movie show time creation processing failed");
        errorResponse.put("message", "Unable to process movie show time creation at this time. Please try again later.");
        errorResponse.put("timestamp", java.time.Instant.now().toString());
        errorResponse.put("internalErrorMessage", e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

}
