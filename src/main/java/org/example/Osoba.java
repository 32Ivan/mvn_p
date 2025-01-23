package org.example;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Covjek")
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ime")
    private String ime;

    @Column(name = "Prezime")
    private String prezime;

    @Column(name = "DatumRodenja")
    private Date datumRodenja;


    public Osoba() {
    }

    public Osoba(Long id, String ime, String prezime, Date datumRodenja) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodenja = datumRodenja;
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

    public Date getDatumRodenja() {
        return datumRodenja;
    }

    public void setDatumRodenja(Date datumRodenja) {
        this.datumRodenja = datumRodenja;
    }
}
