package dev.ulman.restaurant.persistence;

import dev.ulman.restaurant.model.Product;

public interface ProductDao {

    Product getProduct(int id);
    Product getProduct(String name);

    void addProduct(Product product);
    void deleteProduct(int id);

    void updateProduct(int id, Product product);

    boolean isProductExist(String name);

}
