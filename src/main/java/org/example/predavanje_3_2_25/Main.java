package org.example.predavanje_3_2_25;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Author author1 = new Author();
            author1.setName("author1");

            Author author2 = new Author();
            author2.setName("author2");

            Book book1 = new Book();
            book1.setTitle("book1");
            book1.setAuthor(author1);

            Book book2 = new Book();
            book2.setTitle("book2");
            book2.setAuthor(author2);

            Publisher publisher1 = new Publisher();
            publisher1.setName("publisher1");

            Publisher publisher2 = new Publisher();
            publisher2.setName("publisher2");

            Set<Publisher> publishers = new HashSet<>();
            publishers.add(publisher1);
            author1.setPublishers(publishers);

            Set<Publisher> publishers2 = new HashSet<>();
            publishers2.add(publisher2);
            author2.setPublishers(publishers2);

            Set<Author> publishers3 = new HashSet<>();
            publishers3.add(author1);
            publisher1.setAuthors(publishers3);

            Set<Author> publishers4 = new HashSet<>();
            publishers4.add(author2);
            publisher2.setAuthors(publishers4);

            session.persist(author1);
            session.persist(author2);
            session.persist(book1);
            session.persist(book2);
            session.persist(publisher1);
            session.persist(publisher2);

            transaction.commit();
            System.out.println("Uspjesno");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.shutDown();
        }
    }


}

