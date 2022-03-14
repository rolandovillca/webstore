package com.webstore.orderservice.listners;

import com.webstore.orderservice.domains.Order;
import com.webstore.orderservice.events.CreateOrderEvent;
import com.webstore.orderservice.events.OrderEvent;
import com.webstore.orderservice.events.OrderPlacedEvent;
import com.webstore.orderservice.repositories.OrderRepository;
import com.webstore.orderservice.senders.KafkaMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */

@Service
public class CreateOrderListener {
    @Value("${create_order_topic:create-order-topic}")
    private final String createOrderTopic = "";

    Logger logger = LoggerFactory.getLogger(CreateOrderListener.class);
    private final OrderRepository orderRepository;
    private final KafkaMessageSender<OrderEvent> kafkaMessageSender;

    public CreateOrderListener(OrderRepository orderRepository, KafkaMessageSender<OrderEvent> kafkaMessageSender) {
        this.orderRepository = orderRepository;
        this.kafkaMessageSender = kafkaMessageSender;
    }

    @KafkaListener(topics = createOrderTopic)
    public void receive(@Payload CreateOrderEvent createOrderEvent) {
        logger.info("-- Create Order Message Received {} -- ", createOrderEvent);
        Order order = prepareOrder(createOrderEvent);
        orderRepository.save(order);
        kafkaMessageSender.dispatchOrderPlacedEvent(new OrderPlacedEvent(order.getOrderId(), order.getCustomerId(), order.getShippingCartId()));
    }

    private Order prepareOrder(CreateOrderEvent createOrderEvent) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setCustomerId(getLoggedInCustomerId());
        order.setShippingCartId(createOrderEvent.getShoppingCartId());
        return order;
    }

    private String getLoggedInCustomerId() {
        //TODO GET LOGGED IN CUSTOMER ID
        return "123e4567-e89b-12d3-a456-426614174000";
    }

}
