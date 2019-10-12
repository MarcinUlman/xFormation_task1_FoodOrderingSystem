package dev.ulman.restaurant.model;

import java.math.BigDecimal;

public class Product {

    private int id;
    private String name;
    private BigDecimal price;
    private Cuisine cuisine;

    public Product() {
    }

    public Product(String name, BigDecimal price, Cuisine cuisine) {
        this.name = name;
        this.price = price;
        this.cuisine = cuisine;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }
}
