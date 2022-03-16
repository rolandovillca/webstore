package shoppingCartCommandService.shoppingcartcommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ShoppingcartcommandserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartcommandserviceApplication.class, args);
	}

}
