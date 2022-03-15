package shoppingCartCommandService.shoppingcartcommandservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingCartCommandService.shoppingcartcommandservice.model.CartLine;
import shoppingCartCommandService.shoppingcartcommandservice.model.ShoppingCart;
import shoppingCartCommandService.shoppingcartcommandservice.service.ShoppingCartService;

import java.util.List;

@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("createCart")
    public ResponseEntity<ShoppingCart> createShoppingCart() {
        return new ResponseEntity(shoppingCartService.save(), HttpStatus.OK);
    }

    @PostMapping("addProduct/{cartNumber}")
    public ResponseEntity<ShoppingCart> addProduct(@PathVariable String cartNumber, @RequestBody List<CartLine> cartLines) {
        return new ResponseEntity<>(shoppingCartService.addProduct(cartNumber, cartLines), HttpStatus.OK);
    }

    @PostMapping("removeProduct/{cartNumber}")
    public ResponseEntity<ShoppingCart> removeProduct(@PathVariable String cartNumber, @RequestBody CartLine cartLine) {
        return new ResponseEntity<>(shoppingCartService.removeProduct(cartNumber, cartLine), HttpStatus.OK);
    }

    @PostMapping("changeQuantity/{cartNumber}")
    public ResponseEntity<ShoppingCart> changeQuantity(@PathVariable String cartNumber, @RequestBody CartLine cartLine) {
        return new ResponseEntity<>(shoppingCartService.changeQuantity(cartNumber, cartLine), HttpStatus.OK);
    }

    @PostMapping("checkout/{cartNumber}")
    public void checkout(@PathVariable String cartNumber) {
        shoppingCartService.checkout(cartNumber);
    }


}
