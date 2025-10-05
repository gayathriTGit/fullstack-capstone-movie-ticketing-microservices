package com.nmscinemas.ticket_booking_service.controller;

import com.nmscinemas.ticket_booking_service.dto.BookingRequest;
import com.nmscinemas.ticket_booking_service.dto.BookingResponse;
import com.nmscinemas.ticket_booking_service.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/*
 * Simplified Movie Ticket Booking Service REST Controller
 * - Focuses on creating Movie Ticket bookings
 * - Demonstrates microservice communication with price calculator
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    // Constructor-based dependency injection: Spring injects OrderService here
    public BookingController(BookingService service){
        this.bookingService = service;
    }

    /*
     * Main Movie Ticket Booking Creation Endpoint
     * - @PostMapping: Maps HTTP POST requests
     * - @RequestBody: Accepts order request data
     * - Demonstrates microservice communication with ticket price calculator service
     */
    @PostMapping("/create")
    public ResponseEntity<BookingResponse> createBooking(
                            @RequestHeader(value = "X-Gateway-Header", required = false) String gatewayHeader,
                            @Valid @RequestBody BookingRequest request) {
        System.out.println("Calling create booking endpoint...");
//        if (gatewayHeader == null || !gatewayHeader.equals("from-gateway")) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Direct access not allowed. Use api-gateway http://localhost:9500 instead.");
//        }
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }
    /*
     * Health Check Endpoint
     * - Simple endpoint to verify service is running
     * - Important for microservices deployment and orchestration
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Movie Ticket Booking Service is running!");
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
        errorResponse.put("error", "Movie Ticket booking processing failed");
        errorResponse.put("message", "Unable to process movie ticket booking at this time. Please try again later.");
        errorResponse.put("timestamp", java.time.Instant.now().toString());
        errorResponse.put("internalErrorMessage", e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

}
