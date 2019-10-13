package dev.ulman.restaurant.api;

import dev.ulman.restaurant.model.Cuisine;
import dev.ulman.restaurant.model.Order;
import dev.ulman.restaurant.model.Product;
import dev.ulman.restaurant.persistence.*;

import java.math.BigDecimal;
import java.util.List;

public class ApiUtilsImpl implements ApiUtils {

    private OrderDao orderDao;
    private ProductDao productDao;
    private CuisineDao cuisineDao;

    public ApiUtilsImpl() {
        this.cuisineDao = new CuisineDaoImpl();
        this.orderDao = new OrderDaoImpl();
        this.productDao = new ProductDaoImpl();
    }

    @Override
    public void showMenu() {
        List<Cuisine> cuisines = cuisineDao.getAllCuisines();
        for (Cuisine c : cuisines) {
            System.out.println(c.getName());
            cuisineDao.getCuisineProducts(c.getName())
                    .forEach(System.out::println);
        }
    }

    @Override
    public void paidBill() {
        Order order = orderDao.getOrder(1);
        if (order != null)
            System.out.println(orderDao.getOrder(1));
        orderDao.deleteOrder(1);
    }

    @Override
    public void orderLunch(String mainCourse, String dessert) {
        Product product = productDao.getProduct(mainCourse);
        if (product == null || !isMainCourse(product)) {
            System.out.println(mainCourse + " is not main course");
            return;
        }

        Product dessProduct = productDao.getProduct(dessert);
        if (dessProduct == null || !isDessert(dessProduct)) {
            System.out.println(dessert + " is not dessert");
            return;
        }

        checkOrderExist();
        orderDao.addProduct(1, product, 1);
        orderDao.addProduct(1, dessProduct, 1);
    }

    @Override
    public void orderDrink(String drink) {
        Product product = productDao.getProduct(drink);
        if (product == null || !isDrink(product)) {
            System.out.println(drink + " is not drink");
            return;
        }
        checkOrderExist();
        orderDao.addProduct(1, product, 1);
    }

    @Override
    public void orderDrink(String drink, String additionToDrink) {
        Product product = productDao.getProduct(drink);
        if (product == null || !isDrink(product)) {
            System.out.println(drink + " is not drink");
            return;
        }

        Product addProduct = productDao.getProduct(additionToDrink);
        if (addProduct == null || !isAdditionToDrink(addProduct)) {
            System.out.println(additionToDrink + " is not addition to drink");
            return;
        }
        checkOrderExist();
        orderDao.addProduct(1, product, 1);
        orderDao.addProduct(1, addProduct, 1);
    }

    @Override
    public void addNewCuisine(String name) {
        cuisineDao.addCuisine(new Cuisine(name));
    }

    @Override
    public void addNewProduct(String name, BigDecimal price, String cuisineName) {
        Cuisine cuisine = cuisineDao.getCuisine(cuisineName);
        Product product = new Product(name, price, cuisine);
        productDao.addProduct(product);
    }

    private void checkOrderExist() {
        Order order = orderDao.getOrder(1);
        if (order == null) {
            orderDao.addOrder(new Order());
        }
    }

    private boolean isDrink(Product product) {
        String cuisine = product.getCuisine().getName();
        return (cuisine.equals("Drinks"));
    }

    private boolean isDessert(Product product) {
        String cuisine = product.getCuisine().getName();
        return (cuisine.equals("Desserts"));
    }

    private boolean isAdditionToDrink(Product product) {
        String cuisine = product.getCuisine().getName();
        return (cuisine.equals("AdditionToDrink"));
    }

    private boolean isMainCourse(Product product) {
        if (isDrink(product)) return false;
        if (isAdditionToDrink(product)) return false;
        return (!isDessert(product));
    }
}
