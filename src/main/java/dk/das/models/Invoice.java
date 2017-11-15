package dk.das.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Collection;

/**
 * Created by Chris on 15-Nov-17.
 */
@Entity
public class Invoice extends Storable {
    @OneToMany
    private Collection<Product> products;
    @OneToOne
    private Customer customer;

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
