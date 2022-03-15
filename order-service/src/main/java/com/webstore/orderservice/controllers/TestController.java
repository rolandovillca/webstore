package com.webstore.orderservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kojusujan1111@gmail.com 15/03/22
 */
@RestController
@RequestMapping("test")
public class TestController {
    public String test() {
        return "HELLO WORLD";
    }
}
