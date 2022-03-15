package com.webstore.orderservice.controllers;

import com.webstore.orderservice.domains.Order;
import com.webstore.orderservice.events.CartCheckoutEvent;
import com.webstore.orderservice.listners.CartCheckoutListener;
import com.webstore.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */
@RestController
@RequestMapping("orders")
@RefreshScope
public class OrderController {
    private final CartCheckoutListener cartCheckoutListener;
    private final OrderRepository orderRepository;

    @Value("${message}")
    private String message;

    public OrderController(CartCheckoutListener cartCheckoutListener, OrderRepository orderRepository) {
        this.cartCheckoutListener = cartCheckoutListener;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public void place(@RequestBody CartCheckoutEvent cartCheckoutEvent) {
        System.out.println(" --- ORDER PLACED ---");
        this.cartCheckoutListener.receive(cartCheckoutEvent);
    }


    @GetMapping
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }


    @GetMapping("test")
    public String test() {
        return message;
    }
}
