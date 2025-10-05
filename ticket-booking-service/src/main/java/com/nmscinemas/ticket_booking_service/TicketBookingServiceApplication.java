package com.nmscinemas.ticket_booking_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TicketBookingServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(TicketBookingServiceApplication.class, args);
		System.out.println("""
            
            ╔════════════════════════════════════════════════════════════════╗
            ║       TICKET BOOKING SERVICE STARTED                           ║
            ║                                                                ║
            ║  📝 Handles user booking creation and management          	 ║
            ║  🔗 Communicates with price calculator services                ║
            ║  💾 Uses in-memory storage for simplicity                      ║
            ║                                                                ║
            ║  Booking available at: http://localhost:9003/api/bookings 	 ║              
            ╚════════════════════════════════════════════════════════════════╝
            """);

	}
	@Bean
	@LoadBalanced // Enables service discovery - uses service names like "ticket-pricing-service" instead of hardcoded URLs
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

