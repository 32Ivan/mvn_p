package org.example.dz.dz_27_1_25;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Naziv")
    private String naziv;

    @Column(name = "Opis")
    private String opis;

    @Column(name = "Profesor")
    private String profesor;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
    private List<Ocjena> ocjena;

    public List<Ocjena> getOcjena() {
        return ocjena;
    }

    public void setOcjena(List<Ocjena> ocjena) {
        this.ocjena = ocjena;
    }

    public Predmet() {
    }


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
