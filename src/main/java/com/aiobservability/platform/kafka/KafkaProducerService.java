package com.aiobservability.platform.kafka;

import com.aiobservability.platform.core.exception.KafkaPublishException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.KafkaException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "observability.logs";

    private static final Logger logger =
            LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String key, String message) {

        try {
            SendResult<String, String> result =
                    kafkaTemplate.send(TOPIC, key, message)
                            .get(5, TimeUnit.SECONDS);

            logger.info(
                    "Kafka message published topic={} partition={} offset={} key={}",
                    result.getRecordMetadata().topic(),
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset(),
                    key
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            logger.error(
                    "Kafka publish interrupted topic={} key={}",
                    TOPIC,
                    key,
                    e
            );

            throw new KafkaPublishException(
                    "Kafka publish was interrupted",
                    e
            );

        } catch (ExecutionException | java.util.concurrent.TimeoutException e) {

            logger.error(
                    "Failed to publish Kafka message topic={} key={}",
                    TOPIC,
                    key,
                    e
            );

            throw new KafkaPublishException(
                    "Unable to publish event to Kafka",
                    e
            );

        } catch (KafkaException e) {

            logger.error(
                    "Kafka unavailable topic={} key={}",
                    TOPIC,
                    key,
                    e
            );

            throw new KafkaPublishException(
                    "Unable to publish event to Kafka",
                    e
            );
        }
    }
}