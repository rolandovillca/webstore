package shoppingCartQueryService.shoppingcartqueryservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import shoppingCartQueryService.shoppingcartqueryservice.model.ShoppingCart;
import shoppingCartQueryService.shoppingcartqueryservice.service.ShoppingCartService;

public class KafkaShoppingCartListener {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @KafkaListener(topics = "shopping_cart_query_topic")
    public void addProducts(@Payload ShoppingCart shoppingCart) {
        shoppingCartService.saveCart(shoppingCart);
    }
}
