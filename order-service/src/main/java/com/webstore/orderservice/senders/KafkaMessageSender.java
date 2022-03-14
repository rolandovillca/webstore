package com.webstore.orderservice.senders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */
@Service
public class KafkaMessageSender<Event> {

    @Value("${kafka.order_placed_topic}")
    private String orderPlacedTopic;

    private final KafkaTemplate<String, Event> kafkaTemplate;


    public KafkaMessageSender(KafkaTemplate<String, Event> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void dispatchOrderPlacedEvent(Event event) {
        kafkaTemplate.send(orderPlacedTopic, event);
    }
}
