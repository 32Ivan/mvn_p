package org.example.vjezba;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;


public class MainH {
    static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("greska prilikom spajanja " + e.getMessage());
        }
    }

    public static void main(String[] args) {


        save_my_object_to_the_DB();

//        hql_fetch_users();

//        hql_delete_users(4);
        hql_update_users(8, "Lucija");

    }

    public static void save_my_object_to_the_DB() {

        User user = new User("Lisa", LocalDate.now());
        Transaction transaction = null;


        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }

    }

    public static void hql_fetch_users() {
        Transaction transaction = null;


        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            List<User> users = session.createQuery("select u from User u", User.class).list();

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
        Transaction transaction = null;


        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Kreiramo HQL query za brisanje korisnika sa ID-jem
//            String hql = "DELETE FROM User WHERE id = :id";
//            int affectedRows = session.createQuery(hql)
//                    .setParameter("id", userId)
//                    .executeUpdate();
//
//            transaction.commit();
//
//            if (affectedRows > 0) {
//                System.out.println("Korisnik sa ID " + userId + " je obrisan.");
//            } else {
//                System.out.println("Korisnik sa ID " + userId + " ne postoji.");
//            }

            User user = session.get(User.class, userID);
            if (user != null) {

                System.out.println(user.toString());
                session.remove(user);
                transaction.commit();
            } else {
                System.out.println("korisnik nije pronaden");
            }


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }


    public static void hql_update_users(int userID, String name) {
        Transaction transaction = null;


        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();


            // Kreiramo HQL query za update korisnika sa ID-jem
//            String hql = "UPDATE User SET name= :name where  id = :id";
//            int affectedRows = session.createQuery(hql)
//                    .setParameter("name", name)
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

            User user = session.get(User.class, userID);
            if (user != null) {

                System.out.println(user.toString());

                user.setName(name);
                session.merge(user);
                transaction.commit();
            } else {
                System.out.println("korisnik nije pronaden");
            }


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
}
