package com.nmscinemas.admin_movies_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AdminMoviesServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(AdminMoviesServiceApplication.class, args);
		System.out.println("""
            
            ╔════════════════════════════════════════════════════════════════════════════╗
            ║       MOVIES, AUDITORIUMS AND SHOW TIMES ADMIN CREATION SERVICE STARTED    ║
            ║                                                                            ║
            ║  📝 Handles movies, auditoriums and show times creation and                ║
            ║     management - admin pages                                               ║
            ║  💾 Uses in-memory storage for simplicity                                  ║
            ║                                                                            ║
            ║  Movie creation available at: http://localhost:9005/api/movies             ║
            ║  Show time creation available at: http://localhost:9005/api/movieshowtimes ║
            ║  Auditorium creation available at: http://localhost:9005/api/auditoriums   ║
            ╚════════════════════════════════════════════════════════════════════════════╝
            """);

	}

	@Bean
	@LoadBalanced
	// Enables service discovery - uses service names like "ticket-pricing-service" instead of hardcoded URLs
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
