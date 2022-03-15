package com.webstore.orderservice.listners;

import com.webstore.orderservice.domains.Order;
import com.webstore.orderservice.events.CartCheckoutEvent;
import com.webstore.orderservice.events.OrderEvent;
import com.webstore.orderservice.events.OrderPlacedEvent;
import com.webstore.orderservice.repositories.OrderRepository;
import com.webstore.orderservice.senders.KafkaMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */

@Service
public class CartCheckoutListener {
    private static final String SHOPPING_CART_CHECKOUT_TOPIC = "shopping-cart-checkout-topic";
    Logger logger = LoggerFactory.getLogger(CartCheckoutListener.class);
    private final OrderRepository orderRepository;
    private final KafkaMessageSender<OrderEvent> kafkaMessageSender;

    public CartCheckoutListener(OrderRepository orderRepository, KafkaMessageSender<OrderEvent> kafkaMessageSender) {
        this.orderRepository = orderRepository;
        this.kafkaMessageSender = kafkaMessageSender;
    }

 /*   @KafkaListener(topics = SHOPPING_CART_CHECKOUT_TOPIC)*/
    public void receive(@Payload CartCheckoutEvent cartCheckoutEvent) {
        logger.info("-- Cart Checkout Message Received {} -- ", cartCheckoutEvent);
        Order order = prepareOrder(cartCheckoutEvent);
        orderRepository.save(order);
        kafkaMessageSender.dispatchOrderPlacedEvent(new OrderPlacedEvent(order.getOrderId(), order.getCustomerId(), order.getShoppingCartId()));
    }

    private Order prepareOrder(CartCheckoutEvent cartCheckoutEvent) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setCustomerId(getLoggedInCustomerId());
        order.setShoppingCartId(cartCheckoutEvent.getShoppingCartId());
        return order;
    }

    private String getLoggedInCustomerId() {
        //TODO GET LOGGED IN CUSTOMER ID
        return "123e4567-e89b-12d3-a456-426614174000";
    }

}
