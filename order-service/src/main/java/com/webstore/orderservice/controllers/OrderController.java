package com.webstore.orderservice.controllers;

import com.webstore.orderservice.domains.Order;
import com.webstore.orderservice.listners.CartCheckoutListener;
import com.webstore.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */
@RestController
@RequestMapping("orders")
@RefreshScope
public class OrderController {
    private final OrderRepository orderRepository;

    @Value("${message}")
    private String message;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
