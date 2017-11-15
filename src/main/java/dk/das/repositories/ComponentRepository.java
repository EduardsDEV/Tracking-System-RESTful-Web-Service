package dk.das.repositories;

import dk.das.models.Component;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chris on 15-Nov-17.
 */
public interface ComponentRepository extends CrudRepository<Component, Long> {
}
