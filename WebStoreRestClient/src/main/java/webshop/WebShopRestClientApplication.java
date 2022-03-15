package webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import webshop.domain.Product;

import java.util.Arrays;

@SpringBootApplication
public class WebShopRestClientApplication implements CommandLineRunner {

	private RestTemplate restTemplate;

	WebShopRestClientApplication(RestTemplateBuilder restTemplate){
		this.restTemplate = restTemplate.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(WebShopRestClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// add Nike
		restTemplate.postForLocation(RestConstants.PRODUCT_ENDPOINT, new Product("34343","Nike",323.0,"Nike products",234));

		// get Nike
		Product product= restTemplate.getForObject(RestConstants.PRODUCT_ENDPOINT+"/{id}", Product.class, "34343");

		System.out.println("Received:"+ product);


	}

}
