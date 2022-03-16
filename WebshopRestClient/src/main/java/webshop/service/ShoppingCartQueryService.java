package webshop.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import webshop.domain.ShoppingCart;

@FeignClient( name = "shopping-cart-query-service",url = "http://localhost:8079/shopping-cart-query-service")
public interface ShoppingCartQueryService {

    @GetMapping("viewCartDetail/{cartNumber}")
    ShoppingCart viewCartDetail(@PathVariable String cartNumber);

}
