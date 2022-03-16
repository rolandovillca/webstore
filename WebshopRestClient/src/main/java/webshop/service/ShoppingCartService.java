package webshop.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import webshop.domain.CartLine;
import webshop.domain.ShoppingCart;

@FeignClient(name = "shopping-cart-command-service", url = "http://localhost:8079/shopping-cart-command-service")
public interface ShoppingCartService {

    @GetMapping("createCart")
    ShoppingCart createShoppingCart();

    @PostMapping("addProduct/{cartNumber}")
    ShoppingCart addProduct(@PathVariable String cartNumber, @RequestBody CartLine cartLine);

    @PostMapping("removeProduct/{cartNumber}")
    ShoppingCart removeProduct(@PathVariable String cartNumber, @RequestBody CartLine cartLine);

    @PostMapping("changeQuantity/{cartNumber}")
    ShoppingCart changeQuantity(@PathVariable String cartNumber, @RequestBody CartLine cartLine);

    @PostMapping("checkout/{cartNumber}")
    void checkout(@PathVariable String cartNumber);

}
