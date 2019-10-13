package dev.ulman.restaurant.persistence;

import dev.ulman.restaurant.model.Order;
import dev.ulman.restaurant.model.Product;

public interface OrderDao {

    Order getOrder(int id);

    void addOrder(Order order);
    void deleteOrder(int id);

    void addProduct(int id, Product product, int quantity);

}
