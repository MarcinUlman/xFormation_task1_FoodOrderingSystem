package dev.ulman.restaurant.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table (name = "Cuisines")
public class Cuisine {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "CuisineID")
    private int id;
    private String name;
    @OneToMany (mappedBy = "cuisine", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Collection<Product> products;

    public Cuisine() {
    }

    public Cuisine(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return String.format("%2d. %s", id, name);
    }
}
