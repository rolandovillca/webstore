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
    private String shippingCartId;
    private String customerId;

    public Order() {
    }

    public Order(String orderId, String shippingCartId, String customerId) {
        this.orderId = orderId;
        this.shippingCartId = shippingCartId;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShippingCartId() {
        return shippingCartId;
    }

    public void setShippingCartId(String shippingCartId) {
        this.shippingCartId = shippingCartId;
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
                ", shippingCartId='" + shippingCartId + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
