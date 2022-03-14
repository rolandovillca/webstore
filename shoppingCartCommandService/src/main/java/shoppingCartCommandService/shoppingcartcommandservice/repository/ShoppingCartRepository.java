package shoppingCartCommandService.shoppingcartcommandservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shoppingCartCommandService.shoppingcartcommandservice.model.ShoppingCart;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, Integer> {
}
