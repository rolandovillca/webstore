package shoppingCartQueryService.shoppingcartqueryservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import shoppingCartQueryService.shoppingcartqueryservice.model.ShoppingCart;
import shoppingCartQueryService.shoppingcartqueryservice.service.ShoppingCartService;

@Service
public class KafkaShoppingCartListener {

    private static final String CART_QUERY_TOPIC = "shopping_cart_query_topic1";

    private final ShoppingCartService shoppingCartService;

    public KafkaShoppingCartListener(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @KafkaListener(topics = CART_QUERY_TOPIC)
    public void addProducts(@Payload String shoppingCartString) {
        System.out.println("----  CART QUERY TOPIC RECEIVED MESSAGE ---");
        ObjectMapper objectMapper = new ObjectMapper();
        ShoppingCart shoppingCart = null;
        try {
            shoppingCart = objectMapper.readValue(shoppingCartString, ShoppingCart.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        shoppingCartService.saveCart(shoppingCart);
    }
}
