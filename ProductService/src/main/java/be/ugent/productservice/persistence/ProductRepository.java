package be.ugent.productservice.persistence;

import be.ugent.productservice.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
