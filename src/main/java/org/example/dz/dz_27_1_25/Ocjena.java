package org.example.dz.dz_27_1_25;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Ocjena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ocjena")
    private int ocjena;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "ucenikID", nullable = false)
    private Ucenik ucenik;

    @ManyToOne
    @JoinColumn(name = "predmetID", nullable = false)
    private Predmet predmet;

    public Ocjena() {
    }

    public int getOcjena() {
        return ocjena;
    }

    public void setOcjena(int ocjena) {
        if (ocjena < 1 || ocjena > 5) {
            System.out.println("Ocijena moze biti veca od 0 i manja od 6");
            System.out.println("Ocijena je krivo unesena stoga se postavlja na 1");
            this.ocjena = 1;
            setDate(LocalDate.now());
        } else {
            this.ocjena = ocjena;
            setDate(LocalDate.now());

        }

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
}
