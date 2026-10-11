package com.aiobservability.platform.api.controller;

import com.aiobservability.platform.kafka.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class LogsController {

    private final KafkaProducerService kafkaProducerService;

    public LogsController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public ResponseEntity<String> publishLog(
            @RequestParam String serviceName,
            @RequestBody String message) {

        kafkaProducerService.send(serviceName, message);

        return ResponseEntity.accepted()
                .body("Log event accepted for publishing");
    }
}