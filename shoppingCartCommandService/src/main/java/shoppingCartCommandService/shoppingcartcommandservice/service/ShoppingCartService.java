package shoppingCartCommandService.shoppingcartcommandservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingCartCommandService.shoppingcartcommandservice.events.CartCheckoutEvent;
import shoppingCartCommandService.shoppingcartcommandservice.kafka.KafkaCreateOrderSender;
import shoppingCartCommandService.shoppingcartcommandservice.kafka.KafkaShoppingCartQuerySender;
import shoppingCartCommandService.shoppingcartcommandservice.model.CartLine;
import shoppingCartCommandService.shoppingcartcommandservice.model.ShoppingCart;
import shoppingCartCommandService.shoppingcartcommandservice.repository.ShoppingCartRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private KafkaShoppingCartQuerySender kafkaShoppingCartQuerySender;
    @Autowired
    private KafkaCreateOrderSender kafkaCreateOrderSender;
/*    @Autowired
    private RestTemplate restTemplate;*/

    public ShoppingCart addProduct(String cartNumber, List<CartLine> cartLines) {
        System.out.println(" -------- "+cartNumber);
        //Need to check if cart has enough in stock\
        Optional<ShoppingCart> byId = shoppingCartRepository.findById(cartNumber);


        ShoppingCart cart = shoppingCartRepository.findById(cartNumber).orElseThrow(() -> new RuntimeException("Cart Not Found"));
        for (CartLine cartLine : cartLines) {
            if (!this.hasStock(cartLine)) {
                throw new RuntimeException("Stock not available for product with ID:" + cartLine.getProductId());
            }
        }
        cart.setProducts(cartLines);
        kafkaShoppingCartQuerySender.send("shopping_cart_query_topic", cart);
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart removeProduct(String cartNumber, CartLine cartLine) {
        ShoppingCart cart = shoppingCartRepository.findById(cartNumber).orElseThrow(() -> new RuntimeException("Cart Not Found"));

        cart.removeProduct(cartLine);
        kafkaShoppingCartQuerySender.send("shopping_cart_query_topic", cart);
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart changeQuantity(String cartNumber, CartLine newCartLine) {
        ShoppingCart cart = shoppingCartRepository.findById(cartNumber).orElseThrow(() -> new RuntimeException("Cart Not Found"));
        List<CartLine> products = cart.getProducts();
        CartLine previousCartLine = getCurrentCartLine(cartNumber, products);
        if (newCartLine.getQuantity() > previousCartLine.getQuantity()) {
            if (!this.hasStock(newCartLine)) {
                throw new RuntimeException("Stock not available for product with ID:" + newCartLine.getProductId());
            }
        }
        cart.replaceCartLine(previousCartLine, newCartLine);
        kafkaShoppingCartQuerySender.send("shopping_cart_query_topic", cart);
        return shoppingCartRepository.save(cart);
    }

    public void checkout(String cartNumber) {
        //Do we need to get customer ID?
        kafkaCreateOrderSender.send("cart_checkout_topic", new CartCheckoutEvent(cartNumber));
    }


    private CartLine getCurrentCartLine(String cartNumber, List<CartLine> products) {
        for (CartLine cartLine : products) {
            if (cartLine.getCartLineNumber().equalsIgnoreCase(cartNumber)) return cartLine;
        }
        return null;
    }

    private boolean hasStock(CartLine cartLine) {
        //use eureka server and seperate interface for api call.
 /*       Product prod = restTemplate.getForEntity("localhost:8081/products/"+cartLine.getProductId(), Product.class).getBody();
        return prod.getNo_in_stock() < cartLine.getQuantity() ? true : false;*/
        return true;
    }

    public ShoppingCart save() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setShoppingCartNumber(UUID.randomUUID().toString());
        return shoppingCartRepository.save(shoppingCart);
    }
}
