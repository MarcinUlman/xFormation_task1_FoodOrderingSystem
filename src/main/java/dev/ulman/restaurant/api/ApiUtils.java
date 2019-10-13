package dev.ulman.restaurant.api;

import java.math.BigDecimal;

public interface ApiUtils {

    void showMenu();

    void paidBill();

    void orderLunch(String mainCourse, String dessert);

    void orderDrink(String drink);

    void orderDrink(String drink, String additionToDrink);

    void addNewCuisine(String name);

    void addNewProduct(String name, BigDecimal price, String cuisineName);

}
