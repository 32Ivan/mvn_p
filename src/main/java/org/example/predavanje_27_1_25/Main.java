package org.example.predavanje_27_1_25;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;

public class Main {

//    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaExampleUnit");


    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {

        Product product = new Product();
        product.setName("Knjiga122");
        product.setPrice(BigDecimal.valueOf(200));


        Product p = addProduct(product);
        updateProduct(p);
        selectProduct(product);
        deleteProduct(product);

    }

    private static void deleteProduct(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Product p = session.get(Product.class, product.getId());

            session.delete(p);
            System.out.println("ID " + p.getId());

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction.isActive()) {
                transaction.rollback();
            }

        } finally {
            session.close();

        }

    }

    private static void selectProduct(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Product p = session.get(Product.class, product.getId());

            System.out.println("ID : " + p.getId() + " " + p.getName());

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction.isActive()) {
                transaction.rollback();
            }

        } finally {
            session.close();

        }

    }

    private static void updateProduct(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Product p = session.get(Product.class, product.getId());
            p.setName("KNJIGA 22");
            session.update(p);

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction.isActive()) {
                transaction.rollback();
            }

        } finally {
            session.close();

        }

    }

    private static Product addProduct(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(product);

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction.isActive()) {
                transaction.rollback();
            }

        } finally {
            session.close();

        }
        return product;
    }
}
