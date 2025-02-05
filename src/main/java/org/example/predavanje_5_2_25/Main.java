package org.example.predavanje_5_2_25;

import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Author author1 = new Author("author1");
            Author author2 = new Author("author2");

            em.persist(author1);
            em.persist(author2);

            Books book1 = new Books("Book1");
            book1.setAuthor(author1);

            Books book2 = new Books("Book2");
            book2.setAuthor(author2);

            em.persist(book1);
            em.persist(book2);

            Publisher publisher1 = new Publisher("Publisher1");
            Publisher publisher2 = new Publisher("Publisher2");

            em.persist(publisher1);
            em.persist(publisher2);

            Set<Publisher> publishers = new HashSet<>();
            publishers.add(publisher1);
            publishers.add(publisher2);

            book1.setPublishers(publishers);
            book2.setPublishers(publishers);

            em.persist(book1);
            em.persist(book2);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            JpaUtil.getEntityManager().getTransaction().rollback();
        } finally {
            em.close();
        }

        JpaUtil.closeEntityManagerFactory();
    }
}
