package shoppingCartQueryService.shoppingcartqueryservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import shoppingCartQueryService.shoppingcartqueryservice.model.ShoppingCart;
import shoppingCartQueryService.shoppingcartqueryservice.service.ShoppingCartService;

@Service
public class KafkaShoppingCartListener {

    private static final String CART_QUERY_TOPIC = "shopping_cart_query_topic";

    private final ShoppingCartService shoppingCartService;

    public KafkaShoppingCartListener(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @KafkaListener(topics = CART_QUERY_TOPIC)
    public void addProducts(@Payload ShoppingCart shoppingCart) {
        System.out.println("----  CART QUERY TOPIC RECEIVED MESSAGE ---");
        shoppingCartService.saveCart(shoppingCart);
    }
}
