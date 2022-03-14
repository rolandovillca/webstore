package webshop.service;

import webshop.domain.Product;

import java.util.List;

public interface ProductService {

    Product findById(String id);

    List<Product> findAll();

    Product save(Product product);

    Product update(Product product);

    void remove(String id);

    boolean validate(String productNo, int qty);

}
