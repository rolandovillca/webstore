package shoppingCartCommandService.shoppingcartcommandservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingCartCommandService.shoppingcartcommandservice.events.CartCheckoutEvent;
import shoppingCartCommandService.shoppingcartcommandservice.kafka.KafkaCreateOrderSender;
import shoppingCartCommandService.shoppingcartcommandservice.kafka.KafkaShoppingCartQuerySender;
import shoppingCartCommandService.shoppingcartcommandservice.model.CartLine;
import shoppingCartCommandService.shoppingcartcommandservice.model.Product;
import shoppingCartCommandService.shoppingcartcommandservice.model.ShoppingCart;
import shoppingCartCommandService.shoppingcartcommandservice.repository.ShoppingCartRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final KafkaShoppingCartQuerySender kafkaShoppingCartQuerySender;
    private final KafkaCreateOrderSender kafkaCreateOrderSender;
    private final ProductQueryService productQueryService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository,
                               KafkaShoppingCartQuerySender kafkaShoppingCartQuerySender,
                               KafkaCreateOrderSender kafkaCreateOrderSender,
                               ProductQueryService productQueryService) {

        this.shoppingCartRepository = shoppingCartRepository;
        this.kafkaShoppingCartQuerySender = kafkaShoppingCartQuerySender;
        this.kafkaCreateOrderSender = kafkaCreateOrderSender;
        this.productQueryService = productQueryService;
    }

    public ShoppingCart addProduct(String cartNumber, CartLine cartLine) {
        ShoppingCart cart = shoppingCartRepository.findById(cartNumber).orElseThrow(() -> new RuntimeException("Cart Not Found"));
        if (!this.hasStock(cartLine)) {
            throw new RuntimeException("Stock not available for product with ID:" + cartLine.getProductNo());
        }
        cart.addProduct(cartLine);
        kafkaShoppingCartQuerySender.send("shopping_cart_query_topic1", cart);
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart removeProduct(String cartNumber, CartLine cartLine) {
        ShoppingCart cart = shoppingCartRepository.findById(cartNumber).orElseThrow(() -> new RuntimeException("Cart Not Found"));

        cart.removeProduct(cartLine);
        kafkaShoppingCartQuerySender.send("shopping_cart_query_topic1", cart);
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart changeQuantity(String cartNumber, CartLine newCartLine) {
        ShoppingCart cart = shoppingCartRepository.findById(cartNumber).orElseThrow(() -> new RuntimeException("Cart Not Found"));
        List<CartLine> products = cart.getProducts();
        CartLine previousCartLine = getCurrentCartLine(newCartLine, products);
        if (newCartLine.getQuantity() > previousCartLine.getQuantity()) {
            if (!this.hasStock(newCartLine)) {
                throw new RuntimeException("Stock not available for product with ID:" + newCartLine.getProductNo());
            }
        }
        cart.replaceCartLine(previousCartLine, newCartLine);
        kafkaShoppingCartQuerySender.send("shopping_cart_query_topic1", cart);
        return shoppingCartRepository.save(cart);
    }

    public void checkout(String cartNumber) {
        //Do we need to get customer ID?
        kafkaCreateOrderSender.send("shopping-cart-checkout-topic", new CartCheckoutEvent(cartNumber));
    }


    private CartLine getCurrentCartLine(CartLine newCartLine, List<CartLine> products) {
        for (CartLine cartLine : products) {
            if (cartLine.getProductNo() .equalsIgnoreCase(newCartLine.getProductNo()) ) return cartLine;
        }
        return null;
    }

    private boolean hasStock(CartLine cartLine) {
        //use eureka server and seperate interface for api call.
        Product prod = productQueryService.getProduct(cartLine.getProductNo());

        System.out.println("---------No in stock: "+prod+"----");
        return prod.getNoInStock() > cartLine.getQuantity() ? true : false;
    }

    public ShoppingCart save() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setShoppingCartNumber(UUID.randomUUID().toString());
        kafkaShoppingCartQuerySender.send("shopping_cart_query_topic1", shoppingCart);
        return shoppingCartRepository.save(shoppingCart);
    }
}
