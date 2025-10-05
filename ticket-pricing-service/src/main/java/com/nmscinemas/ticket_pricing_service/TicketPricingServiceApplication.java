package com.nmscinemas.ticket_pricing_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketPricingServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(TicketPricingServiceApplication.class, args);
        System.out.println("""
            
            ╔════════════════════════════════════════════════════════════════╗
            ║              TICKET PRICING SERVICE STARTED                    ║
            ║                                                                ║
            ║  📝 Handles movie ticket booking fare calculation              ║
            ║  🔗 Communicates with Booking service                          ║
            ║                                                                ║
            ║                                                                ║
            ║  Available at: http://localhost:9004/api/pricecalculator       ║
            ╚════════════════════════════════════════════════════════════════╝
            """);
    }

}
