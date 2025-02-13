package org.example.parcijalni_ispit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {


        while (true) {
            System.out.println("Odaberi jednu opciju");
            System.out.println("Unesi 1 za dodavanje novog polaznika");
            System.out.println("Unesi 2 za dodavanje novog programa");
            System.out.println("Unesi 3 za upis polaznika na program");
            System.out.println("Unesi 4 za prebacivanje polaznika iz jednog u drugi program");
            System.out.println("Unesi 5 za ispis svih polaznika na svim programima");
            System.out.println("Unesi 7 za izlaz iz programa");
            System.out.println("------------------------------------");
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                scanner.nextLine();

                switch (num) {
                    case 1 -> dodajNovogPolaznika();
                    case 2 -> noviProgramObrazovanja();
                    case 3 -> upisiPolaznikaNaProgram();
                    case 4 -> prebaciPolaznikaUdrugiProgram();
                    case 5 -> ispisPolaznikaNaProgramima();
                    case 7 -> {
                        System.out.println("Izlaz");
                        return;
                    }
                    default -> System.out.println("Krivi unos pokusaj ponovo");

                }
            } else {
                System.out.println("Molimo unesite valjan broj.");
                scanner.nextLine();
            }
            System.out.println("------------------------------------");
        }

    }


    private static void dodajNovogPolaznika() {
        Transaction transaction = null;

        System.out.println("Unesi ime polaznika");
        String ime = scanner.nextLine();

        System.out.println("Unesi prezime polaznika");
        String prezime = scanner.nextLine();

        Polaznik polaznik = new Polaznik(ime, prezime);

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.persist(polaznik);

            System.out.println("Uspjesno dodan u bazu " + ime + prezime);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }

    }

    private static void ispisPolaznikaNaProgramima() {
        Transaction transaction = null;


        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            List<Upis> upis = session.createQuery("select u from Upis u", Upis.class).list();

            upis.forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }


    private static void noviProgramObrazovanja() {
        Transaction transaction = null;

        System.out.println("Unesi naziv programa obrazovanja");
        String naziv = scanner.nextLine();

        System.out.println("Unesi CSVET programa obrazovanja");
        int CSVET = scanner.nextInt();

        ProgramObrazovanja programObrazovanja = new ProgramObrazovanja(naziv, CSVET);

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.persist(programObrazovanja);

            System.out.println("Uspjesno dodan u bazu " + naziv);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }


    }

    private static void upisiPolaznikaNaProgram() {
        Transaction transaction = null;

        System.out.println("---------------");
        sviProgrami();
        System.out.println("---------------");

        System.out.println("Unesi ID programa obrazovanja koji zelite da polaznik ide");
        int programId = scanner.nextInt();

        System.out.println("---------------");
        sviPolaznici();
        System.out.println("---------------");

        System.out.println("Unesi ID polaznika");
        int polaznikId = scanner.nextInt();


        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            Polaznik polaznik = session.get(Polaznik.class, polaznikId);

            ProgramObrazovanja programObrazovanja = session.get(ProgramObrazovanja.class, programId);

            Upis upis = new Upis(polaznik, programObrazovanja);

            session.persist(upis);

            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }


    private static void prebaciPolaznikaUdrugiProgram() {

        Transaction transaction = null;
        System.out.println("---------------");
        sviProgrami();
        System.out.println("---------------");

        System.out.println("Unesi ID programa obrazovanja koji polaznik ide");
        int programId = scanner.nextInt();

        System.out.println("Unesi ID programa obrazovanja na koji se polaznik prebacuje");
        int programIdNovi = scanner.nextInt();

        System.out.println("---------------");
        sviPolaznici();
        System.out.println("---------------");

        System.out.println("Unesi ID polaznika");
        int polaznikId = scanner.nextInt();

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            Polaznik polaznik = session.get(Polaznik.class, polaznikId);

            ProgramObrazovanja programObrazovanja = session.get(ProgramObrazovanja.class, programIdNovi);

            Upis upis = (Upis) session.createQuery("FROM Upis u WHERE u.polaznik.id = :polaznikId AND u.programObrazovanja.id = :programId")
                    .setParameter("polaznikId", polaznikId)
                    .setParameter("programId", programId)
                    .uniqueResult();

//            upis.setPolaznik(polaznik);
            upis.setProgramObrazovanja(programObrazovanja);

            session.update(upis);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }

    }

    static void sviProgrami() {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            List<ProgramObrazovanja> ProgramObrazovanja = session.createQuery("select p from ProgramObrazovanja p", ProgramObrazovanja.class).list();

            ProgramObrazovanja.forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }


    static void sviPolaznici() {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            List<Polaznik> polaznik = session.createQuery("select p from Polaznik p", Polaznik.class).list();

            polaznik.forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }


}