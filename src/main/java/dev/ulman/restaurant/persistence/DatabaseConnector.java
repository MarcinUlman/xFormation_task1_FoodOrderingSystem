package dev.ulman.restaurant.persistence;

import org.hibernate.SessionFactory;

public class DatabaseConnector {

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
}
