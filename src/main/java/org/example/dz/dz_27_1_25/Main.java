package org.example.dz.dz_27_1_25;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaExampleUnit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        //kreiranje ucenika
        Ucenik ucenik1 = new Ucenik();
        ucenik1.setIme("Ivo");
        ucenik1.setPrezime("Ivic");

        Ucenik ucenik2 = new Ucenik();
        ucenik2.setIme("Ante");
        ucenik2.setPrezime("Antic");

        // kreiranje predmeta
        Predmet matematika = new Predmet();
        matematika.setNaziv("Matematika");
        matematika.setOpis("Osnove matematike");
        matematika.setProfesor("Matematic");

        Predmet fizika = new Predmet();
        fizika.setNaziv("Fizika");
        fizika.setOpis("Osnove fizike");
        fizika.setProfesor("Fizic");

        // kreiranje ocjena
        Ocjena ocjena1 = new Ocjena();
        ocjena1.setOcjena(5);
        ocjena1.setDate(LocalDate.now());
        ocjena1.setUcenik(ucenik1);
        ocjena1.setPredmet(matematika);

        Ocjena ocjena2 = new Ocjena();
        ocjena2.setOcjena(4);
        ocjena2.setDate(LocalDate.now());
        ocjena2.setUcenik(ucenik1);
        ocjena2.setPredmet(fizika);

        Ocjena ocjena3 = new Ocjena();
        ocjena3.setOcjena(3);
        ocjena3.setDate(LocalDate.now());
        ocjena3.setUcenik(ucenik2);
        ocjena3.setPredmet(matematika);

        //dodavanje u bazu
        em.persist(ucenik1);
        em.persist(ucenik2);
        em.persist(matematika);
        em.persist(fizika);
        em.persist(ocjena1);
        em.persist(ocjena2);
        em.persist(ocjena3);

        em.getTransaction().commit();

        //dohvacanje  podataka
        System.out.println("Predmeti koje slusa Ivo:");
        List<Predmet> predmetiMarko = em.createQuery(
                        "SELECT o.predmet FROM Ocjena o WHERE o.ucenik.ime = :ime", Predmet.class)
                .setParameter("ime", "Ivo")
                .getResultList();
        predmetiMarko.forEach(p -> System.out.println("Predmet : " + p.getNaziv()));

        System.out.println("Ocjene za Matematiku:");
        List<Ocjena> ocjeneMatematika = em.createQuery(
                        "SELECT o FROM Ocjena o WHERE o.predmet.naziv = :naziv", Ocjena.class)
                .setParameter("naziv", "Matematika")
                .getResultList();
        ocjeneMatematika.forEach(o -> System.out.println("Ime ucenike i ocjena, " + o.getUcenik().getIme() + ": " + o.getOcjena()));

        System.out.println("Prosjek za Fiziku:");
        Double prosjekFizika = em.createQuery(
                        "SELECT AVG(o.ocjena) FROM Ocjena o WHERE o.predmet.naziv = :naziv", Double.class)
                .setParameter("naziv", "Fizika")
                .getSingleResult();
        System.out.println("prosjek ocjena je : " + prosjekFizika);

        em.close();
        emf.close();
    }
}

