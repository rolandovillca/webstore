package webshop.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import webshop.domain.Product;

import java.util.List;


@FeignClient("product-service")
public interface ProductService {

    @GetMapping("/products/{productNumber}")
    Product get(@PathVariable("productNumber") String
                                   productNumber);
    @GetMapping("/products")
    List<Product> get();

    @PostMapping("/products")
    Product create(@RequestBody Product product);

    @PutMapping("/products/{productNumber}")
    Product update(@RequestBody Product product,@PathVariable("productNumber") String productNumber);



}
