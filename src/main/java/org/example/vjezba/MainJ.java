package org.example.vjezba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class MainJ {

    static EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("JpaExampleUnit");
        } catch (Exception e) {
            System.out.println("Konekcija nije uspostavljena");
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        save_my_object_to_the_DB();

        hql_fetch_users();
//        hql_delete_users(4);
//        hql_update_users(9, "Liam");
    }

    public static void save_my_object_to_the_DB() {

        User user = new User("Lisa", LocalDate.now());

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(user);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

    }

    public static void hql_fetch_users() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            List<User> users = entityManager.createQuery("select u from User u", User.class).getResultList();

            users.forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

    public static void hql_delete_users(int userID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

//             Kreiramo HQL query za brisanje korisnika sa ID-jem
//            String hql = "DELETE FROM User WHERE id = :id";
//            int affectedRows = entityManager.createQuery(hql)
//                    .setParameter("id", userID)
//                    .executeUpdate();
//
//            transaction.commit();
//
//            if (affectedRows > 0) {
//                System.out.println("Korisnik sa ID " + userID + " je obrisan.");
//            } else {
//                System.out.println("Korisnik sa ID " + userID + " ne postoji.");
//            }

            User user = entityManager.find(User.class, userID);
            if (user != null) {

                System.out.println(user.toString());
                entityManager.remove(user);
                transaction.commit();
            } else {
                System.out.println("korisnik nije pronaden");
            }

        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

    public static void hql_update_users(int userID, String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();


            // Kreiramo HQL query za update korisnika sa ID-jem
//            String hql = "UPDATE User SET name= :name where  id = :id";
//            int affectedRows = entityManager.createQuery(hql)
//                    .setParameter("name", name)
//                    .setParameter("id", userID)
//                    .executeUpdate();
//
//            transaction.commit();
//
//            if (affectedRows > 0) {
//                System.out.println("Korisnik sa ID " + userID + " je azuriran.");
//            } else {
//                System.out.println("Korisnik sa ID " + userID + " ne azuriran.");
//            }

            User user = entityManager.find(User.class, userID);
            if (user != null) {

                System.out.println(user.toString());

                user.setName(name);
                entityManager.merge(user);
                transaction.commit();
            } else {
                System.out.println("korisnik nije pronaden");
            }


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}