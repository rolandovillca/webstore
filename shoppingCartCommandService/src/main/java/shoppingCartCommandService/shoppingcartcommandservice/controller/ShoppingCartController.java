package shoppingCartCommandService.shoppingcartcommandservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingCartCommandService.shoppingcartcommandservice.model.CartLine;
import shoppingCartCommandService.shoppingcartcommandservice.model.ShoppingCart;
import shoppingCartCommandService.shoppingcartcommandservice.service.ShoppingCartService;

@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/createCart")
    public ResponseEntity<ShoppingCart> createShoppingCart() {
        return new ResponseEntity(new ShoppingCart(), HttpStatus.OK);
    }

    @PostMapping("/addProduct/{cartNumber}")
    public ResponseEntity<ShoppingCart> addProduct(@PathVariable int cartNumber, @RequestBody CartLine cartLine) {
        return new ResponseEntity<>(shoppingCartService.addProduct(cartNumber, cartLine), HttpStatus.OK);
    }

    @PostMapping("/removeProduct/{cartNumber}")
    public ResponseEntity<ShoppingCart> removeProduct(@PathVariable int cartNumber, @RequestBody CartLine cartLine) {
        return new ResponseEntity<>(shoppingCartService.removeProduct(cartNumber, cartLine), HttpStatus.OK);
    }

    @PostMapping("/changeQuantity/{cartNumber}")
    public ResponseEntity<ShoppingCart> changeQuantity(@PathVariable int cartNumber, @RequestBody CartLine cartLine) {
        return new ResponseEntity<>(shoppingCartService.changeQuantity(cartNumber, cartLine), HttpStatus.OK);
    }

    @PostMapping("/checkout/{cartNumber}")
    public void checkout(@PathVariable int cartNumber) {
        shoppingCartService.checkout(cartNumber);
    }



}
