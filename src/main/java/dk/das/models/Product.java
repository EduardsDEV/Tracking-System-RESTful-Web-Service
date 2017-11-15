package dk.das.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Created by Chris on 15-Nov-17.
 */
@Entity
public class Product extends Storable {
    @OneToMany
    private Collection<Component> components;

    public Collection<Component> getComponents() {
        return components;
    }

    public void setComponents(Collection<Component> components) {
        this.components = components;
    }
}
