package dev.ulman.restaurant.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table (name = "Products")
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column (name = "ProductID")
    private int id;
    private String name;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn (name = "CuisineID")
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

    @Override
    public String toString() {
        return String.format("%3d. %-30s %6.2f", id, name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) &&
                price.equals(product.price) &&
                cuisine.equals(product.cuisine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, cuisine);
    }
}
