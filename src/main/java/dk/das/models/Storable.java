package dk.das.models;

import javax.persistence.*;

/**
 * Created by Chris on 15-Nov-17.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Storable {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true, nullable = false)
    private String uniqueCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}
