package webshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import webshop.domain.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
