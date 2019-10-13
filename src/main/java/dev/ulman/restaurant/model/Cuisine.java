package dev.ulman.restaurant.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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
        products = new ArrayList<>();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuisine cuisine = (Cuisine) o;
        return name.equals(cuisine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
