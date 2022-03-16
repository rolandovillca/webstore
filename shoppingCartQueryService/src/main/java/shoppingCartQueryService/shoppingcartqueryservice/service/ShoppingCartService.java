package shoppingCartQueryService.shoppingcartqueryservice.service;

import org.springframework.stereotype.Service;
import shoppingCartQueryService.shoppingcartqueryservice.model.ShoppingCart;
import shoppingCartQueryService.shoppingcartqueryservice.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void saveCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getCart(String cartNumber) {
        return shoppingCartRepository.findById(cartNumber).orElseThrow(() -> new RuntimeException("No Cart Found!!!"));
    }
}
