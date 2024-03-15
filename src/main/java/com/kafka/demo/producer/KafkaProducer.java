package com.kafka.demo.producer;

import com.kafka.demo.avro.model.TransactionEvent;
import com.kafka.demo.properties.KafkaCustomProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;
    private final KafkaCustomProperties kafkaCustomProperties;

    public void sendMessage(TransactionEvent transactionEvent) {
        ProducerRecord<String, TransactionEvent> producerRecord = new ProducerRecord<>(kafkaCustomProperties.getTransactionTopic(), transactionEvent);
        CompletableFuture<SendResult<String, TransactionEvent>> completableFuture = kafkaTemplate.send(producerRecord);
        log.info("Sending kafka message on topic {}", kafkaCustomProperties.getTransactionTopic());

        completableFuture.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Kafka message successfully sent on topic {} and value {}", kafkaCustomProperties.getTransactionTopic(), result.getProducerRecord().value().toString());
            } else {
                log.error("An error occurred while sending kafka message for event with value {}", producerRecord);
            }
        });
    }
}
