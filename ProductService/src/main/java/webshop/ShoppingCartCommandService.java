package webshop;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kojusujan1111@gmail.com 04/03/22
 */
@FeignClient(name = "shopping-cart-query-service")
public interface ShoppingCartCommandService {
    @RequestMapping("viewCartDetail/{cartNumber}")
    int getCartDetail(@PathVariable String cartNumber);
}
