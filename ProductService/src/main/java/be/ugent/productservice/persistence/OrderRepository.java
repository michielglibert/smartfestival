package be.ugent.productservice.persistence;

import be.ugent.productservice.domain.Order;
import be.ugent.productservice.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

}
