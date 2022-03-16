package shoppingCartCommandService.shoppingcartcommandservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import shoppingCartCommandService.shoppingcartcommandservice.model.Product;

@FeignClient(name = "product-service")
public interface ProductQueryService {

    @RequestMapping("/products/{productId}")
    Product getProduct(@PathVariable String productId);
}
