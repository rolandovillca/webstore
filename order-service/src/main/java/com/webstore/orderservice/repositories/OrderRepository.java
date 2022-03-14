package com.webstore.orderservice.repositories;

import com.webstore.orderservice.domains.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
