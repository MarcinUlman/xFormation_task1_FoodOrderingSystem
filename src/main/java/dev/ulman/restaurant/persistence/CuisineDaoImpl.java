package dev.ulman.restaurant.persistence;

import dev.ulman.restaurant.model.Cuisine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CuisineDaoImpl implements CuisineDao {

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public Cuisine getCuisineById(int id) {
        Cuisine cuisine = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            cuisine = session.get(Cuisine.class, id);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return cuisine;
    }

    @Override
    public Cuisine getCuisineByName(String name) {
        Cuisine cuisine = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Cuisine> criteriaQuery = criteriaBuilder.createQuery(Cuisine.class);
            Root<Cuisine> root = criteriaQuery.from(Cuisine.class);

            criteriaQuery.select(root)
                    .where(criteriaBuilder.equal(root.get("name"), name));

            Query<Cuisine> query = session.createQuery(criteriaQuery);

            cuisine = query.getSingleResult();
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
        return cuisine;
    }

    @Override
    public void addCuisine(Cuisine cuisine) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(cuisine);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void deleteCuisine(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Cuisine cuisine = session.get(Cuisine.class, id);
            if (cuisine != null) session.delete(cuisine);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void updateCuisine(int id, Cuisine cuisine) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            cuisine.setId(id);
            session.saveOrUpdate(cuisine);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }
}
