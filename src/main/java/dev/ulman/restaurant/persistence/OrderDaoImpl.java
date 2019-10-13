package dev.ulman.restaurant.persistence;

import dev.ulman.restaurant.model.Order;
import dev.ulman.restaurant.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class OrderDaoImpl implements OrderDao {

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public Order getOrder(int id) {
        Order order = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            order = session.get(Order.class, id);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return order;
    }

    @Override
    public void addOrder(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void deleteOrder(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Order order = session.get(Order.class, id);
            if (order == null) {
                transaction.commit();
                System.out.println("Order does not exist.");
                return;
            }
            session.delete(order);
            transaction.commit();
            System.out.println("Order successfully deleted");
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void addProduct(int id, Product product, int quantity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            Order order = session.get(Order.class, id);
            if(order == null){
                System.out.println("Order does not exist");
                return;
            }

            ProductDao productDao = new ProductDaoImpl();
            if (!productDao.isProductExist(product.getName())) {
                System.out.println("Product does not exist. Add it first");
                return;
            }

            order.addProduct(product, quantity);
            session.merge(order);

            transaction.commit();
            System.out.println("Product successful add to order");
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }


}
