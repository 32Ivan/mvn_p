package org.example.predavanje_23_1_25;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaExampleUnit");
        EntityManager em = emf.createEntityManager();

        Person person = new Person();
        person.setName("Pokusaj1");

        Person person2 = new Person();
        person2.setName("Pokusaj2");


        Company company = new Company();
        company.setName("PokusajCom1");

        Company company2 = new Company();
        company2.setName("PokusajCom2");

        Contract contract = new Contract();
        contract.setIznosPlace(BigDecimal.valueOf(200));
        contract.setPocetakUgovora(LocalDate.now());

        Set<Contract> contractSet = new HashSet<>();
        contractSet.add(contract);

        Set<Person> personSet = new HashSet<>();
        personSet.add(person);
        personSet.add(person2);

        person.setContracts(contractSet);
        contract.setPersons(personSet);

        company.setContracts(contractSet);
        contract.setCompany(company);

        em.getTransaction().begin();
        em.persist(company);
        em.persist(company2);
        em.persist(person);
        em.persist(person2);
        em.persist(contract);
        em.getTransaction().commit();

        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        for (Person p : query.getResultList()) {
            System.out.println("Person Name: " + p.getName());

            System.out.println("----------Person------------");
        }

        TypedQuery<Company> query2 = em.createQuery("SELECT c FROM Company c", Company.class);
        for (Company c : query2.getResultList()) {
            System.out.println("Company Name: " + c.getName());
            System.out.println("----------Company------------");
        }

        em.close();
        emf.close();

    }
}
