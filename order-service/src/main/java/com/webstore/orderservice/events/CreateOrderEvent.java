package com.webstore.orderservice.events;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */

public class CreateOrderEvent {
    private String shoppingCartId;

    public CreateOrderEvent() {
    }

    public CreateOrderEvent(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }
}
