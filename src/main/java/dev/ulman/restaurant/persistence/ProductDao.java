package dev.ulman.restaurant.persistence;

import dev.ulman.restaurant.model.Product;

public interface ProductDao {

    Product getProductById(int id);

    void addProduct(Product product);
    void deleteProduct(int id);

    Product updateProduct(int id, Product product);

}
