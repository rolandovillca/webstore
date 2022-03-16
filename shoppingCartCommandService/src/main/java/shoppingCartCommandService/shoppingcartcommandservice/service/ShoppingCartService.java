package shoppingCartCommandService.shoppingcartcommandservice.service;

import org.springframework.stereotype.Service;
import shoppingCartCommandService.shoppingcartcommandservice.events.CartCheckoutEvent;
import shoppingCartCommandService.shoppingcartcommandservice.kafka.KafkaCreateOrderSender;
import shoppingCartCommandService.shoppingcartcommandservice.kafka.KafkaShoppingCartQuerySender;
import shoppingCartCommandService.shoppingcartcommandservice.model.CartLine;
import shoppingCartCommandService.shoppingcartcommandservice.model.Product;
import shoppingCartCommandService.shoppingcartcommandservice.model.ShoppingCart;
import shoppingCartCommandService.shoppingcartcommandservice.repository.ShoppingCartRepository;

import java.util.UUID;

@Service
public class ShoppingCartService {

    private static final String SHOPPING_CART_QUERY_TOPIC = "shopping_cart_query_topic1";
    private static final String SHOPPING_CART_CHECKOUT_TOPIC = "shopping-cart-checkout-topic";

    private final ShoppingCartRepository shoppingCartRepository;
    private final KafkaShoppingCartQuerySender kafkaShoppingCartQuerySender;
    private final KafkaCreateOrderSender kafkaCreateOrderSender;
    private final ProductQueryService productQueryService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, KafkaShoppingCartQuerySender kafkaShoppingCartQuerySender, KafkaCreateOrderSender kafkaCreateOrderSender, ProductQueryService productQueryService) {

        this.shoppingCartRepository = shoppingCartRepository;
        this.kafkaShoppingCartQuerySender = kafkaShoppingCartQuerySender;
        this.kafkaCreateOrderSender = kafkaCreateOrderSender;
        this.productQueryService = productQueryService;
    }

    public ShoppingCart addProduct(String cartNumber, CartLine cartLine) {
        ShoppingCart cart = getShoppingCartByCartNumber(cartNumber);
        validateStockAvailability(cartLine);
        cart.addProduct(cartLine);
        kafkaShoppingCartQuerySender.send(SHOPPING_CART_QUERY_TOPIC, cart);
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart removeProduct(String cartNumber, CartLine cartLine) {
        ShoppingCart cart = getShoppingCartByCartNumber(cartNumber);
        cart.removeProduct(cartLine);
        kafkaShoppingCartQuerySender.send(SHOPPING_CART_QUERY_TOPIC, cart);
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart changeQuantity(String cartNumber, CartLine newCartLine) {
        ShoppingCart cart = getShoppingCartByCartNumber(cartNumber);
        validateStockAvailability(newCartLine);
        cart.changeQuantity(newCartLine);
        kafkaShoppingCartQuerySender.send(SHOPPING_CART_QUERY_TOPIC, cart);
        return shoppingCartRepository.save(cart);
    }

    private ShoppingCart getShoppingCartByCartNumber(String cartNumber) {
        return shoppingCartRepository.findById(cartNumber).orElseThrow(() -> new RuntimeException("Cart Not Found"));
    }

    public void checkout(String cartNumber) {
        kafkaCreateOrderSender.send(SHOPPING_CART_CHECKOUT_TOPIC, new CartCheckoutEvent(cartNumber));
    }

    private boolean hasStock(CartLine cartLine) {
        Product product = productQueryService.getProduct(cartLine.getProductNo());
        System.out.println("---------No in stock: " + product + "----");
        return product.getNoInStock() > cartLine.getQuantity();
    }

    public ShoppingCart save() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setShoppingCartNumber(UUID.randomUUID().toString());
        kafkaShoppingCartQuerySender.send(SHOPPING_CART_QUERY_TOPIC, shoppingCart);
        return shoppingCartRepository.save(shoppingCart);
    }

    private void validateStockAvailability(CartLine cartLine) {
        if (!this.hasStock(cartLine)) {
            throw new RuntimeException("Stock not available for product with ID:" + cartLine.getProductNo());
        }
    }

}
