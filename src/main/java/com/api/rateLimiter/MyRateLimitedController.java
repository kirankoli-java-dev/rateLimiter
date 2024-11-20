package com.api.rateLimiter;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyRateLimitedController {

    // Apply rate limiting using Resilience4j's @RateLimiter annotation
    @PostMapping("/process")
    @RateLimiter(name = "myRateLimiter", fallbackMethod = "rateLimiterFallback")
    public ResponseEntity<String> processRequest(@RequestBody String data) {
        // Simulated business logic
    	
        String response = "Processed data: " + data;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fallback method when rate limiting triggers
    public ResponseEntity<String> rateLimiterFallback(String data, Throwable t) {
        return new ResponseEntity<>("Rate limit exceeded. Please try again later.", HttpStatus.TOO_MANY_REQUESTS);
    }
}

