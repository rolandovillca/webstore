package webshop.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import webshop.ShoppingQueryCommandService;
import webshop.domain.Product;
import webshop.domain.ShoppingCart;
import webshop.events.OrderPlacedEvent;
import webshop.service.ProductService;

@Component
@Slf4j
public class StockUpdateListener {
    private final ProductService productService;
    private final ShoppingQueryCommandService shoppingCartCommandService;

    public StockUpdateListener(ProductService productService, ShoppingQueryCommandService shoppingCartCommandService) {
        this.productService = productService;
        this.shoppingCartCommandService = shoppingCartCommandService;
    }

    @KafkaListener(topics = "order-placed-topic-1")
    public void handleUpdateStock(@Payload String orderPlacedEventStringFormat) {

        ObjectMapper objectMapper = new ObjectMapper();
        OrderPlacedEvent orderPlacedEvent = null;
        try {
            orderPlacedEvent = objectMapper.readValue(orderPlacedEventStringFormat, OrderPlacedEvent.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ShoppingCart shoppingCart = getShoppingCartDetail(orderPlacedEvent.getShippingCartId());
        shoppingCart.getProducts().forEach(cartLine -> {
            Product product = productService.findById(cartLine.getProductId());
            int quantity = product.getNoInStock() - cartLine.getQuantity();
            log.info("Product quantity updated with value:" + quantity);
            product.setNoInStock(quantity);
            productService.update(product);
        });
    }

    private ShoppingCart getShoppingCartDetail(String shippingCartId) {
        return this.shoppingCartCommandService.getCartDetail(shippingCartId);
    }
}
