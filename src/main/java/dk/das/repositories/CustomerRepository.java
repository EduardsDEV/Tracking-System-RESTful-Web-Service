package dk.das.repositories;

import dk.das.models.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chris on 15-Nov-17.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
