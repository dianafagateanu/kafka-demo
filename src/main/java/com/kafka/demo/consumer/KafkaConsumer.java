package com.kafka.demo.consumer;

import com.kafka.demo.avro.model.TransactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    @KafkaListener(topics = "${kafka.transaction-topic}", containerFactory = "kafkaListenerContainerFactory", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(Message<TransactionEvent> transactionEventMessage) {
        log.info("Starting consuming from transaction_events_topic - {}", transactionEventMessage.toString());
    }
}
