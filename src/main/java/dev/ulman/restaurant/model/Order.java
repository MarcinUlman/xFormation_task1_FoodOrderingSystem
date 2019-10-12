package dev.ulman.restaurant.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "OrderID")
    private int id;
    @ElementCollection
    @MapKeyColumn (name = "ProductName")
    @Column (name = "Quantity")
    private Map<Product, Integer> products;
    @Column (name = "Order_Total_Cost")
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
