package dev.ulman.restaurant.model;

import java.util.Collection;

public class Cuisine {

    private int id;
    private String name;
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
}
