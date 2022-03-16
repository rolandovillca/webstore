package shoppingCartQueryService.shoppingcartqueryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import shoppingCartQueryService.shoppingcartqueryservice.model.ShoppingCart;
import shoppingCartQueryService.shoppingcartqueryservice.service.ShoppingCartService;

@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("viewCartDetail/{cartNumber}")
    public ResponseEntity<ShoppingCart> viewCartDetail(@PathVariable String cartNumber) {
        return new ResponseEntity<>(shoppingCartService.getCart(cartNumber), HttpStatus.OK);
    }
}
