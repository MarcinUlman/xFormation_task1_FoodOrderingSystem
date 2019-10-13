package dev.ulman.restaurant.persistence;

import dev.ulman.restaurant.model.Cuisine;
import dev.ulman.restaurant.model.Product;

import javax.persistence.NoResultException;
import java.util.List;

public interface CuisineDao {

    Cuisine getCuisine(int id);
    Cuisine getCuisine(String name);

    void addCuisine(Cuisine cuisine);
    void deleteCuisine(int id);

    List<Cuisine> getAllCuisines();
    List<Product> getCuisineProducts(String cuisineName);

    boolean isCuisineExist(String name);
}
