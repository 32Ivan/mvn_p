package org.example.predavanje_27_1_25;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaExampleUnit");


    public static void main(String[] args) {

        Product product = new Product();
        product.setName("Knjiga1");
        product.setPrice(BigDecimal.valueOf(200));


        Product p = addProduct(product);
//        updateProduct(product);
//        selectProduct(product);
//        deleteProduct(product);

    }

    private static void deleteProduct(Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();

            et.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (et.isActive()) {
                et.rollback();
            }

        } finally {
            em.close();
        }
    }

    private static void selectProduct(Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            et.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (et.isActive()) {
                et.rollback();
            }

        } finally {
            em.close();
        }

    }

    private static void updateProduct(Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Product p = em.find(Product.class, product.getId());
            System.out.println(p.getId());
            p.setName("Knjiga36");

            em.merge(p);
            et.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (et.isActive()) {
                et.rollback();
            }

        } finally {
            em.close();
        }

    }

    private static Product addProduct(Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(product);
            et.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (et.isActive()) {
                et.rollback();
            }

        } finally {
            em.close();

        }
        return product;
    }
}
