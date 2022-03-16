package webshop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import webshop.domain.CartLine;
import webshop.domain.Product;
import webshop.domain.ShoppingCart;
import webshop.service.ProductService;
import webshop.service.ShoppingCartQueryService;
import webshop.service.ShoppingCartService;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class WebShopRestClientApplication implements CommandLineRunner {

    ProductService productService;

    ShoppingCartService shoppingCartService;

    ShoppingCartQueryService shoppingCartQueryService;

    WebShopRestClientApplication(ProductService productService, ShoppingCartService shoppingCartService, ShoppingCartQueryService shoppingCartQueryService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartQueryService = shoppingCartQueryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebShopRestClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("============ WELCOME TO WEB SHOP APP ===============");
        Product product1 = new Product("75467", "Nike", 56.6, "This is test", 60);
        Product product2 = new Product("75468", "SunnyD", 50.6, "This is test", 60);
        log.info("Creating 2 products");
        productService.create(product1);
        productService.create(product2);

        log.info("Updating product:75467");
        product1.setPrice(100);
        productService.update(product1, "75467");

        log.info("Getting products information");
        List<Product> products = productService.get();
        log.info("Received product with size:" + products.size());
        log.info(products.toString());

        log.info("Getting single product information");
        Product product4 = productService.get("75467");
        log.info(product4.toString());


        log.info("============Creating shopping cart ===============");
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart();

        log.info("============Adding product to shopping cart ===============");
        shoppingCartService.addProduct(shoppingCart.getShoppingCartNumber(), new CartLine("75467", 4));
        shoppingCartService.addProduct(shoppingCart.getShoppingCartNumber(), new CartLine("75468", 4));

        log.info("============Updating shopping cart ===============");
        shoppingCartService.changeQuantity(shoppingCart.getShoppingCartNumber(), new CartLine("75467", 5));


        log.info("============Viewing shopping shopping cart ===============");
        ShoppingCart viewShoppingCart = shoppingCartQueryService.viewCartDetail(shoppingCart.getShoppingCartNumber());
        log.info("============ Shopping Cart = {} ===============",viewShoppingCart);

        log.info("============Removing product to shopping cart ===============");
        shoppingCartService.removeProduct(shoppingCart.getShoppingCartNumber(), new CartLine("75468", 4));


        log.info("============Checkout shopping cart ===============");
        shoppingCartService.checkout(shoppingCart.getShoppingCartNumber());


    }
}
