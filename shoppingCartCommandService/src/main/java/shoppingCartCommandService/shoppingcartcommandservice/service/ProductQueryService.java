package shoppingCartCommandService.shoppingcartcommandservice.service;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import shoppingCartCommandService.shoppingcartcommandservice.configs.LoadBalancerConfiguration;
import shoppingCartCommandService.shoppingcartcommandservice.model.Product;

@FeignClient(name = "product-service")
@LoadBalancerClient(name = "product-service", configuration = LoadBalancerConfiguration.class)
public interface ProductQueryService {

    @RequestMapping("/products/{productId}")
    Product getProduct(@PathVariable String productId);
}
