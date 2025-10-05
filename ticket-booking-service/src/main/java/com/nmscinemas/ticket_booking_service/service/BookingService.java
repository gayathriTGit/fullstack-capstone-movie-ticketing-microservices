package com.nmscinemas.ticket_booking_service.service;

import com.nmscinemas.ticket_booking_service.dto.*;
import com.nmscinemas.ticket_booking_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingService {

    private final RestTemplate restTemplate;

    private BookingRepository bookingRepository;

    // The ticket price calculator service URL is now injected from configuration for flexibility and best practices.
    @Value("${ticket-calculator.service-url}")
    private String ticketPriceCalcServiceUrl;

    @Autowired
    public BookingService(RestTemplate restTemplate, BookingRepository bookingRepo) {
        this.restTemplate = restTemplate;
        this.bookingRepository = bookingRepo;
    }

    /*
     * Main Movie Ticket Booking Creation Method
     * - Creates a movie ticket booking that
     *   calculates pricing through microservice call
     * - Demonstrates the core pattern: ticket booking creation -> ticker price calculation
     */
    public BookingResponse createBooking(BookingRequest request) {
        System.out.println("Calling movie booking create in booking service...");

        // Create ticket price calculator request
        BookingPriceCalcRequest priceCalcRequest = new BookingPriceCalcRequest();
        priceCalcRequest.setMovieName(request.getMovieName());
        priceCalcRequest.setNoOfTickets(request.getNoOfTickets());
        priceCalcRequest.setShowTime(request.getShowTime());
        priceCalcRequest.setNoOfDrinks(request.getNoOfDrinks());
        priceCalcRequest.setNoOfPopcorns(request.getNoOfPopcorns());
        priceCalcRequest.setNoOfIceCream(request.getNoOfIceCream());
        priceCalcRequest.setNoOfCandy(request.getNoOfCandy());
        priceCalcRequest.setBaseTicketPrice(request.getBaseTicketPrice());

        // Call ticket price calculator service to calculate total using RestTemplate
        // Best Practice: Use configuration for service URLs instead of hardcoding
        BookingPriceCalcResponse priceCalcResponse = null;
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<BookingPriceCalcRequest> entity = new HttpEntity<>(priceCalcRequest, headers);
            ResponseEntity<BookingPriceCalcResponse> response = restTemplate.postForEntity(ticketPriceCalcServiceUrl, entity, BookingPriceCalcResponse.class);
            priceCalcResponse = response.getBody();
        } catch (RestClientException e) {
            // Log the error for debugging
            System.err.println("Ticket Price Calculator service unavailable: " + e.getMessage());
            // Continue with ticket booking creation using fallback pricing
        }

        // Create booking response
        String status = priceCalcResponse != null ? "COST_CONFIRMED" : "PENDING_PRICING";
        double totalCost = priceCalcResponse != null ? priceCalcResponse.getTotalPrice() : 0.0;

        BookingResponse bookingResponse = new BookingResponse(
                            request.getMovieId(),
                            request.getAuditoriumId(),
                            request.getNoOfTickets(),
                            request.getShowTime(),
                            totalCost,
                            status,
                            request.getSeats());

        // Save the booking to the database
        bookingResponse = saveBooking(bookingResponse);

        return bookingResponse;

    }

    // Save the booking to the database
    public BookingResponse saveBooking(BookingResponse booking){  return bookingRepository.add(booking);}

    public BookingResponse getBookingById(Long id){
        return bookingRepository.findById(id);
    }
}
