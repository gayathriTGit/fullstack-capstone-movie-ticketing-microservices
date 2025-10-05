package com.nmscinemas.api_gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Circuit Breaker Fallback Controller
 *
 * When microservices are down or responding slowly, the circuit breaker pattern
 * prevents cascading failures by providing fallback responses instead of letting
 * requests hang or fail completely.
 *
 * Circuit Breaker States:
 * 1. CLOSED: Normal operation, requests flow through
 * 2. OPEN: Service is failing, fallback is used immediately
 * 3. HALF-OPEN: Testing if service has recovered
 *
 * Benefits:
 * - Prevents cascade failures
 * - Improves user experience with meaningful error messages
 * - Gives failing services time to recover
 * - Provides system stability under load
 *
 * This controller provides user-friendly fallback responses for each service.
 */
@RestController
public class FallbackController {

    /**
     * Admin Movies List Service Fallback
     *
     * When the Admin Movies List Service is down or slow, this fallback provides a
     * user-friendly response instead of a system error.
     */
    @RequestMapping("/fallback/movieslist")
    public ResponseEntity<Map<String, Object>> moviesListFallback() {
        return createFallbackResponse(
                "Admin Movies List Service Temporarily Unavailable",
                "We're experiencing high demand. Please try calling the movies list again in a few moments.",
                "ADMIN_MOVIES_LIST_SERVICE_DOWN"
        );
    }

    /**
     * Admin Movies Show Times List Service Fallback
     *
     * When the Admin Movies Show Times List Service is down or slow, this fallback provides a
     * user-friendly response instead of a system error.
     */
    @RequestMapping("/fallback/moviesshowtimes")
    public ResponseEntity<Map<String, Object>> moviesShowTimesFallback() {
        return createFallbackResponse(
                "Admin Movies Show Times List Service Temporarily Unavailable",
                "We're experiencing high demand. Please try calling the movies show times list again in a few moments.",
                "ADMIN_MOVIES_SHOW_TIMES_LIST_SERVICE_DOWN"
        );
    }

    /**
     * Admin Movies Show Times for a Given Auditorium List Service Fallback
     *
     * When the Admin Movies Show Times List Service is down or slow, this fallback provides a
     * user-friendly response instead of a system error.
     */
    @RequestMapping("/fallback/moviesshowtimes/auditoriums")
    public ResponseEntity<Map<String, Object>> showTimesAuditoriumsFallback() {
        return createFallbackResponse(
                "Admin Movies Show Times for a given auditorium List Service Temporarily Unavailable",
                "We're experiencing high demand. Please try calling the movies show times for a given auditorium list again in a few moments.",
                "ADMIN_MOVIES_SHOW_TIMES_AUDITORIUM_LIST_SERVICE_DOWN"
        );
    }

    /**
     * Admin Movies Show Times for a Given Auditorium and Given Movie List Service Fallback
     *
     * When the Admin Movies Show Times List Service is down or slow, this fallback provides a
     * user-friendly response instead of a system error.
     */
    @RequestMapping("/fallback/moviesshowtimes/movies")
    public ResponseEntity<Map<String, Object>> showTimesMoviesFallback() {
        return createFallbackResponse(
                "Admin Movies Show Times for a given movie List Service Temporarily Unavailable",
                "We're experiencing high demand. Please try calling the movies show times for a given movie list again in a few moments.",
                "ADMIN_MOVIES_SHOW_TIMES_AUDITORIUM_LIST_SERVICE_DOWN"
        );
    }

    /**
     * Admin Auditoriums List Service Fallback
     *
     * When the Admin Auditoriums List Service is down or slow, this fallback provides a
     * user-friendly response instead of a system error.
     */
    @RequestMapping("/fallback/auditoriums")
    public ResponseEntity<Map<String, Object>> auditoriumsListFallback() {
        return createFallbackResponse(
                "Admin Auditoriums List Service Temporarily Unavailable",
                "We're experiencing high demand. Please try calling the auditoriums list again in a few moments.",
                "ADMIN_AUDITORIUMS_LIST_SERVICE_DOWN"
        );
    }

    /**
     * Admin Movies Images uploads Service Fallback
     *
     * When the Admin Movies Images uploads Service is down or slow, this fallback provides a
     * user-friendly response instead of a system error.
     */
    @RequestMapping("/fallback/movies/uploads")
    public ResponseEntity<Map<String, Object>> moviesUploadsFallback() {
        return createFallbackResponse(
                "Admin Movies Images Uploads Service Temporarily Unavailable",
                "We're experiencing high demand. Please try calling the movies images upload again in a few moments.",
                "ADMIN_MOVIES_UPLOAD_SERVICE_DOWN"
        );
    }

    /**
     * Ticket Booking Service Fallback
     *
     * When the Ticket Booking Service is down or slow, this fallback provides a
     * user-friendly response instead of a system error.
     */
    @RequestMapping("/fallback/bookings")
    public ResponseEntity<Map<String, Object>> bookingServiceFallback() {
        return createFallbackResponse(
                "Ticket Booking Service Temporarily Unavailable",
                "We're experiencing high demand. Please try placing your booking again in a few moments.",
                "TICKET_BOOKING_SERVICE_DOWN"
        );
    }

    /**
     * Ticket Fare Calculator Service Fallback
     *
     * When the Cab Fare Calculator Service is down, cab booking can't be processed because
     * we can't calculate the total cost. This provides clear feedback to users that the service is down.
     */
    @RequestMapping("/fallback/pricecalculator")
    public ResponseEntity<Map<String, Object>> fareCalculatorServiceFallback() {
        return createFallbackResponse(
                "Price Calculator Service Temporarily Unavailable",
                "Unable to calculate ticket price right now. Please try again shortly.",
                "PRICE_CALCULATOR_SERVICE_DOWN"
        );
    }

    /**
     * Standardized Fallback Response
     *
     * Creates a consistent fallback response structure that:
     * - Provides clear error messaging
     * - Includes timestamps for debugging
     * - Uses appropriate HTTP status codes
     * - Gives actionable guidance to users
     */
    private ResponseEntity<Map<String, Object>> createFallbackResponse(String title, String message, String errorCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", true);
        response.put("title", title);
        response.put("message", message);
        response.put("errorCode", errorCode);
        response.put("timestamp", LocalDateTime.now());
        response.put("suggestion", "This is a temporary issue. The service will be restored shortly.");

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}
