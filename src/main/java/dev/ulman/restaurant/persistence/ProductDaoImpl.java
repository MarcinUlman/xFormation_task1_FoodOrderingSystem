package dev.ulman.restaurant.persistence;

import dev.ulman.restaurant.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ProductDaoImpl implements ProductDao {

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public Product getProduct(int id) {
        Product product = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            product = session.get(Product.class, id);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return product;
    }

    @Override
    public Product getProduct(String name) {
        Product product = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);

            criteriaQuery.select(root)
                    .where(criteriaBuilder.equal(root.get("name"), name));

            Query<Product> query = session.createQuery(criteriaQuery);

            try {
                product = query.getSingleResult();
            } catch (NoResultException e){
                product = null;
            }
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return product;
    }

    @Override
    public void addProduct(Product product) {
        if (isProductExist(product.getName())) {
            System.out.printf("Product named '%s' already exist\n", product.getName());
            return;
        }
        CuisineDao cuisineDao = new CuisineDaoImpl();
        if(!cuisineDao.isCuisineExist(product.getCuisine().getName())) {
            System.out.println("Cuisine does not exist. Add it first");
            return;
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            System.out.println("Product successfully added");
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void deleteProduct(int id) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product == null) {
                transaction.commit();
                System.out.println("Product does not exist. Add it first");
                return;
            }
            session.delete(product);
            transaction.commit();
            System.out.println("Product successfully deleted");
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void updateProduct(int id, Product product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Product existingProduct = session.get(Product.class, id);
            if (existingProduct == null) {
                transaction.commit();
                System.out.println("Could not modify product because it's not exist. Add it first.");
                return;
            }

            CuisineDao cuisineDao = new CuisineDaoImpl();
            if(!cuisineDao.isCuisineExist(product.getCuisine().getName())) {
                System.out.println("Cuisine does not exist. Add it first");
                return;
            }

            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCuisine(product.getCuisine());
            session.update(existingProduct);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public boolean isProductExist(String name) {
            return getProduct(name) != null;
        }
}