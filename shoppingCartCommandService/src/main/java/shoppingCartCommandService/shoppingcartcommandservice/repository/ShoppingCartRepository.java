package shoppingCartCommandService.shoppingcartcommandservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shoppingCartCommandService.shoppingcartcommandservice.model.ShoppingCart;
@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
}
