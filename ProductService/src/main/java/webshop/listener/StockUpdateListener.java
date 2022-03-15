package webshop.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import webshop.domain.Product;
import webshop.domain.ShoppingCart;
import webshop.events.OrderPlacedEvent;
import webshop.service.ProductService;

@Component
@Slf4j
public class StockUpdateListener {

    private final ProductService productService;

    public StockUpdateListener(ProductService productService) {
        this.productService = productService;
    }

    @KafkaListener(topics = "order-placed-topic")
    public void handleUpdateStock(@Payload OrderPlacedEvent orderPlacedEvent) {

        ShoppingCart shoppingCart = getShoppingCartDetail(orderPlacedEvent.getShippingCartId());

        shoppingCart.getProducts().forEach(cartLine -> {
            Product product = productService.findById(cartLine.getProductId());
            int quantity = product.getNoInStock() - productStockDTO.qty();
            log.info("Product quantity updated with value:" + quantity);
            product.setNoInStock(quantity);
            productService.update(product);

        });


    }

    private ShoppingCart getShoppingCartDetail(String shippingCartId) {
    }

    private record ProductStockDTO(String productNumber, int qty) {
    }

}
