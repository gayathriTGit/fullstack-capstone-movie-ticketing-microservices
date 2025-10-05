package com.nmscinemas.ticket_pricing_service.controller;

import com.nmscinemas.ticket_pricing_service.dto.BookingPriceCalcRequest;
import com.nmscinemas.ticket_pricing_service.dto.BookingPriceCalcResponse;
import com.nmscinemas.ticket_pricing_service.service.BookingPriceCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

/*
 * Simplified Movie Ticket Price Calculator Controller
 * - Focuses on core movie ticket price calculation functionality
 * - Demonstrates microservice communication pattern
 */
@RestController
@RequestMapping("/api/pricecalculator")
@Validated
public class BookingPriceCalcController {

    private final BookingPriceCalcService calculatorService;

    @Autowired
    BookingPriceCalcController(BookingPriceCalcService service){
        this.calculatorService = service;
    }

    /*
     * Main Ticket Booking Price Calculator Endpoint
     * - POST /api/pricecalculator/calculate
     * - This is the core endpoint that other microservices will call
     * - @Valid annotation triggers validation on the request body
     * - Returns pricing calculation
     */
    @PostMapping("/calculate")
    public ResponseEntity<BookingPriceCalcResponse> calculateBookingPrice(
                                    @RequestHeader(value = "X-Gateway-Header", required = false) String gatewayHeader,
                                    @Valid @RequestBody BookingPriceCalcRequest request) {
        try {
            BookingPriceCalcResponse response = calculatorService.calculateBookingPrice(request);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            System.err.println("Error calculating booking fare: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /*
     * Health Check Endpoint
     * - Simple endpoint to verify service is running
     * - Important for microservices deployment and orchestration
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Movie Ticket Booking Price Calculator Service is running!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Validation failed");
        return ResponseEntity.badRequest().body(error);
    }

}
