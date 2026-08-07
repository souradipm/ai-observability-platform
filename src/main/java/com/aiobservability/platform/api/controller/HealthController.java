package com.aiobservability.platform.api.controller;


import com.aiobservability.platform.api.dto.response.ApiResponse;
import com.aiobservability.platform.core.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HealthController {


    private static final Logger logger =
            LoggerFactory.getLogger(HealthController.class);


    @GetMapping("/health")
    public ApiResponse<String> health() {

        logger.debug("Received health check request");

//        logger.info("Health check completed successfully");

        logger.debug("Health check completed successfully");

        return ApiResponse.success(
                "Application is healthy",
                "AI Observability Platform is running"
        );
//        throw new RuntimeException("Something went wrong");
    }
}