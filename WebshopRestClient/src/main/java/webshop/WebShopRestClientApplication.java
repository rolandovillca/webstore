package webshop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import webshop.domain.CartLine;
import webshop.domain.Product;
import webshop.domain.ShoppingCart;
import webshop.service.ProductService;
import webshop.service.ShoppingCartService;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class WebShopRestClientApplication implements CommandLineRunner {

	ProductService productService;

	ShoppingCartService shoppingCartService;

	WebShopRestClientApplication(ProductService productService,ShoppingCartService shoppingCartService){
		this.productService = productService;
		this.shoppingCartService = shoppingCartService;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebShopRestClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product1 = new Product("75467", "Nike", 56.6, "This is test",60);
		Product product2 = new Product("75468", "SunnyD", 50.6, "This is test",60);
		log.info("Creating product");
		productService.create(product1);
		productService.create(product2);

		log.info("Updating product:75467");
		product1.setPrice(100);
		productService.update(product1,"75467");

		log.info("Getting products information");
		List<Product> products = productService.get();
		log.info("Received product with size:"+ products.size());
		products.forEach(System.out::println);

		log.info("Getting single product information");
		Product product4 = productService.get("75467");
		System.out.println(product4);


		log.info("============Creating shopping cart ===============");
		ShoppingCart shoppingCart = shoppingCartService.createShoppingCart();

		log.info("============Adding product to shopping cart ===============");
		shoppingCartService.addProduct(shoppingCart.getShoppingCartNumber(),new CartLine("75467",4));
		shoppingCartService.addProduct(shoppingCart.getShoppingCartNumber(),new CartLine("75468",4));

		log.info("============Updating shopping cart ===============");
		shoppingCart = shoppingCartService.changeQuantity(shoppingCart.getShoppingCartNumber(),new CartLine("75467",5));


		log.info("============Removing product to shopping cart ===============");
		shoppingCartService.removeProduct(shoppingCart.getShoppingCartNumber(),new CartLine("75468",4));


		/*log.info("============Checkout shopping cart ===============");
		shoppingCartService.checkout(shoppingCart.getShoppingCartNumber());*/




	}
}
