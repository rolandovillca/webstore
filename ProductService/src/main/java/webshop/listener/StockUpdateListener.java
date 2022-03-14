package webshop.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import webshop.domain.Product;
import webshop.service.ProductService;

@Configuration
@Slf4j
public class StockUpdateListener {

    private ProductService productService;

    public StockUpdateListener(ProductService productService){
        this.productService = productService;
    }

    @KafkaListener(topics = "update-product-stock-topic")
    public void handleUpdateStock(@Payload ProductStockDTO productStockDTO, ConsumerRecord<String, ProductStockDTO> cr) {
        log.info("Topic [update-product-stock-topic] Received message from product number {} with {} quantity ", productStockDTO.productNumber, productStockDTO.qty);
        Product product = productService.findById(productStockDTO.productNumber());
        int quantity = product.getNoInStock() - productStockDTO.qty();
        log.info("Product quantity updated with value:"+quantity);
        product.setNoInStock(quantity);
        productService.update(product);
    }

    private record ProductStockDTO(String productNumber, int qty) { }

}
