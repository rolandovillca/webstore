package com.webstore.orderservice.senders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webstore.orderservice.events.OrderPlacedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */
@Service
public class KafkaMessageSender {

    @Value("${kafka.order_placed_topic}")
    private String orderPlacedTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;


    public KafkaMessageSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void dispatchOrderPlacedEvent(OrderPlacedEvent event) {
        ObjectMapper objectMapper = new ObjectMapper();
        String eventInString = "";
        try {
            eventInString = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(orderPlacedTopic, eventInString);

    }
}
