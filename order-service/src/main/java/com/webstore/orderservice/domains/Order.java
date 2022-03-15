package com.webstore.orderservice.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */
@Document
public class Order {
    @Id
    private String orderId;
    private String shoppingCartId;
    private String customerId;

    public Order() {
    }

    public Order(String orderId, String shoppingCartId, String customerId) {
        this.orderId = orderId;
        this.shoppingCartId = shoppingCartId;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", shippingCartId='" + shoppingCartId + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
