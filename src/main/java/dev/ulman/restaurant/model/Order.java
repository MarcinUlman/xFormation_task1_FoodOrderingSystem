package dev.ulman.restaurant.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "OrderID")
    private int id;
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn (name = "ProductName")
    @Column (name = "Quantity")
    private Map<Product, Integer> products;
    @Column (name = "Order_Total_Cost")
    private BigDecimal totalCost;

    public Order() {
    this.totalCost = new BigDecimal(0.00);
    this.products = new HashMap<Product, Integer>();
    }

    public void addProduct(Product product, int quantity){
        if (!this.products.containsKey(product)){
            this.products.put(product, quantity);
            BigDecimal costToAdd = new BigDecimal(quantity).multiply(product.getPrice());
            this.totalCost = this.totalCost.add(costToAdd);
        } else {
            BigDecimal costToAdd = new BigDecimal(quantity).multiply(product.getPrice());
            quantity += this.products.get(product);
            if (quantity <= 0) removeProduct(product);
            else {
                this.totalCost = this.totalCost.add(costToAdd);
                this.products.put(product, quantity);
            }
        }
    }

    private void removeProduct(Product product) {
        if (products.isEmpty()) return;
        BigDecimal costToSubtract  = new BigDecimal(products.get(product)).multiply(product.getPrice());
        this.totalCost.subtract(costToSubtract);
        this.products.remove(product);
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

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");

        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Product, Integer>> iter = products.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Product, Integer> entry = iter.next();
            Product product = entry.getKey();
            String productName = product.getName();
            sb.append(productName);
            int len = 35 - productName.length();
            while (len-- >= 0) {
                sb.append('.');
            }
            int quantity = entry.getValue();
            sb.append(String.format("%2d", quantity)).append(" x");
            BigDecimal price = product.getPrice();
            sb.append(String.format("%6s", decimalFormat.format(price)));
            sb.append(" ..");
            sb.append(String.format("%6s", decimalFormat.format(price.multiply(new BigDecimal(quantity)))));
            sb.append('\n');
        }

        String total = "Total price:";
        sb.append(total);
        int len = 48 - total.length();
        while (len-- > 0) {
            sb.append('.');
        }
        sb.append(String.format("%7s", decimalFormat.format(this.totalCost)));
        return sb.toString();
    }
}
