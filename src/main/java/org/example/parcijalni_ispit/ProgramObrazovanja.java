package org.example.parcijalni_ispit;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class ProgramObrazovanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProgramObrazovanjaID")
    private Long id;

    @Column(name = "Naziv", nullable = false)
    private String Naziv;

    @Column(name = "CSVET", nullable = false)
    private Integer CSVET;

    @OneToMany(mappedBy = "programObrazovanja", cascade = CascadeType.ALL)
    private Set<Upis> upisi;

    public ProgramObrazovanja() {
    }

    public ProgramObrazovanja(String naziv, Integer CSVET) {

        Naziv = naziv;
        this.CSVET = CSVET;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String naziv) {
        Naziv = naziv;
    }

    public Integer getCSVET() {
        return CSVET;
    }

    public void setCSVET(Integer CSVET) {
        this.CSVET = CSVET;
    }

    @Override
    public String toString() {
        return "ProgramObrazovanja{" +
                "id=" + id +
                ", Naziv='" + Naziv + '\'' +
                ", CSVET=" + CSVET +
                '}';
    }
}
