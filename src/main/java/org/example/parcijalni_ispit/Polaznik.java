package org.example.parcijalni_ispit;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Polaznik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PolaznikID")
    private Long id;

    @Column(name = "Ime", nullable = false)
    private String Ime;

    @Column(name = "Prezime", nullable = false)
    private String Prezime;

    @OneToMany(mappedBy = "polaznik", cascade = CascadeType.ALL)
    private Set<Upis> upisi;


    public Polaznik() {
    }

    public Polaznik(String ime, String prezime) {
        Ime = ime;
        Prezime = prezime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        Prezime = prezime;
    }

    @Override
    public String toString() {
        return "Polaznik{" +
                "id=" + id +
                ", Ime='" + Ime + '\'' +
                ", Prezime='" + Prezime + '\'' +
                '}';
    }
}
