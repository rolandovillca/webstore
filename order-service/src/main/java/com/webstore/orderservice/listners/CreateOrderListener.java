package com.webstore.orderservice.listners;

import com.webstore.orderservice.domains.Order;
import com.webstore.orderservice.events.CreateOrderEvent;
import com.webstore.orderservice.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */


@Component
public class CreateOrderListener {
    Logger logger = LoggerFactory.getLogger(CreateOrderListener.class);
    private final OrderRepository orderRepository;

    public CreateOrderListener(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "create_order_topic")
    public void receive(@Payload CreateOrderEvent createOrderEvent) {
        logger.info("-- Create Order Message Received {} -- ", createOrderEvent);
        Order order = prepareOrder(createOrderEvent);
        orderRepository.save(order);
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
