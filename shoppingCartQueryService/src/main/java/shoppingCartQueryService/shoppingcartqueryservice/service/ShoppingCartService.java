package shoppingCartQueryService.shoppingcartqueryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingCartQueryService.shoppingcartqueryservice.model.ShoppingCart;
import shoppingCartQueryService.shoppingcartqueryservice.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void saveCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getCart(int cartNumber) {
        return shoppingCartRepository.findById(cartNumber).orElseThrow(() -> new RuntimeException("No Cart Found!!!"));
    }
}
