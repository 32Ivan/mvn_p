package org.example.predavanje_23_1_25;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pocetakUgovora")
    private LocalDate pocetakUgovora;

    @Column(name = "iznosPlace")
    private BigDecimal iznosPlace;

    @ManyToMany(mappedBy = "contracts")
    private Set<Person> persons;

    public Contract() {
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPocetakUgovora() {
        return pocetakUgovora;
    }

    public void setPocetakUgovora(LocalDate pocetakUgovora) {
        this.pocetakUgovora = pocetakUgovora;
    }

    public BigDecimal getIznosPlace() {
        return iznosPlace;
    }

    public void setIznosPlace(BigDecimal iznosPlace) {
        this.iznosPlace = iznosPlace;
    }
}
