package dev.ulman.restaurant.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private int id;
    private Map<Product, Integer> products;
    private BigDecimal totalCost;

    public Order() {
    this.totalCost = new BigDecimal(0.00);
    this.products = new HashMap<Product, Integer>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
