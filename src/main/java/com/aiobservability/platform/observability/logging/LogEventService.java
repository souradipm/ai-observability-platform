package com.aiobservability.platform.observability.logging;

import org.springframework.stereotype.Service;

@Service
public class LogEventService {

    private final LogEventRepository repository;

    public LogEventService(LogEventRepository repository) {
        this.repository = repository;
    }

    public LogEvent save(LogEvent event) {
        return repository.save(event);
    }
}