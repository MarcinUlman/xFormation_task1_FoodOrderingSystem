package dev.ulman.restaurant.persistence;

import dev.ulman.restaurant.model.Product;

public interface ProductDao {

    Product getProductById(int id);
    Product getProductByName(String name);

    void addProduct(Product product);
    void deleteProduct(int id);

    void updateProduct(int id, Product product);

}
