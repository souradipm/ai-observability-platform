package com.aiobservability.platform.kafka;

import com.aiobservability.platform.observability.logging.LogEvent;
import com.aiobservability.platform.observability.logging.LogEventService;
import tools.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class KafkaConsumerService {

    private static final Logger logger =
            LoggerFactory.getLogger(KafkaConsumerService.class);

    private final LogEventService logEventService;
    private final ObjectMapper objectMapper;

    public KafkaConsumerService(
            LogEventService logEventService,
            ObjectMapper objectMapper) {

        this.logEventService = logEventService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = "observability.logs",
            groupId = "observability-consumer-group"
    )
    public void consume(ConsumerRecord<String, String> record) {

        logger.info(
                "Kafka message consumed topic={} partition={} offset={} key={} message={}",
                record.topic(),
                record.partition(),
                record.offset(),
                record.key(),
                record.value()
        );

        try {

            LogEvent logEvent =
                    objectMapper.readValue(record.value(), LogEvent.class);

            logEvent.setId(null);
            logEvent.setTimestamp(Instant.now());
            logEvent.setServiceName(record.key());

            LogEvent savedEvent =
                    logEventService.save(logEvent);

            logger.info(
                    "Log event stored in Elasticsearch id={} serviceName={}",
                    savedEvent.getId(),
                    savedEvent.getServiceName()
            );

        } catch (Exception exception) {

            logger.error(
                    "Failed to process Kafka message topic={} partition={} offset={}",
                    record.topic(),
                    record.partition(),
                    record.offset(),
                    exception
            );
            throw exception;
        }
    }
}