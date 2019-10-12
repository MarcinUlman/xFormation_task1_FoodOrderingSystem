package dev.ulman.restaurant.persistence;

import dev.ulman.restaurant.model.Cuisine;

public interface CuisineDao {

    Cuisine getCuisineById(int id);

    void addCuisine(Cuisine cuisine);
    void deleteCuisine(int id);

    void updateCuisine(int id, Cuisine cuisine);
}
