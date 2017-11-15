package dk.das.repositories;

import dk.das.models.Invoice;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chris on 15-Nov-17.
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
