package dev.ulman.restaurant.persistence;

import dev.ulman.restaurant.model.Cuisine;

public interface CuisineDao {

    Cuisine getCuisineById(int id);
    Cuisine getCuisineByName(String name);

    void addCuisine(Cuisine cuisine);
    void deleteCuisine(int id);

    void updateCuisine(int id, Cuisine cuisine);
}
