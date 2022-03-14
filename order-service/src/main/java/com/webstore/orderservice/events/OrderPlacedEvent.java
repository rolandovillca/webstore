package com.webstore.orderservice.events;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */

public class OrderPlacedEvent {
    private String orderId;
    private String shippingCartId;
    private String customerId;

    public OrderPlacedEvent(String orderId, String shippingCartId, String customerId) {
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
        return "OrderPlacedEvent{" +
                "orderId='" + orderId + '\'' +
                ", shippingCartId='" + shippingCartId + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
