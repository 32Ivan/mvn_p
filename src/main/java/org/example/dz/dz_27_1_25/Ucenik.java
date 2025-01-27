package org.example.dz.dz_27_1_25;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ucenik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ime")
    private String ime;

    @Column(name = "Prezime")
    private String prezime;

    @OneToMany(mappedBy = "ucenik", cascade = CascadeType.ALL)
    private List<Ocjena> ocjena;

    public List<Ocjena> getOcjena() {
        return ocjena;
    }

    public void setOcjena(List<Ocjena> ocjena) {
        this.ocjena = ocjena;
    }

    public Ucenik() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
