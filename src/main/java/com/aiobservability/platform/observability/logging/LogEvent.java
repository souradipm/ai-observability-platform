package com.aiobservability.platform.observability.logging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "observability-logs")
public class LogEvent {

    @Id
    private String id;

    private Instant timestamp;

    private String serviceName;

    private String event;

    private String status;

    private String message;
}