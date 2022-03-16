package shoppingCartQueryService.shoppingcartqueryservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shoppingCartQueryService.shoppingcartqueryservice.model.ShoppingCart;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
}
