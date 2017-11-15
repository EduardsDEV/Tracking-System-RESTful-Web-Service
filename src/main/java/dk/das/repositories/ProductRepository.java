package dk.das.repositories;

import dk.das.models.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chris on 15-Nov-17.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
}
