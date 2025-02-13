package org.example.parcijalni_ispit;

import jakarta.persistence.*;

@Entity
public class Upis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UpisID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDPolaznik", nullable = false)
    private Polaznik polaznik;

    @ManyToOne
    @JoinColumn(name = "IDProgramObrazovanja", nullable = false)
    private ProgramObrazovanja programObrazovanja;


    public Upis() {
    }

    public Upis(Polaznik polaznik, ProgramObrazovanja programObrazovanja) {
        this.polaznik = polaznik;
        this.programObrazovanja = programObrazovanja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    public ProgramObrazovanja getProgramObrazovanja() {
        return programObrazovanja;
    }

    public void setProgramObrazovanja(ProgramObrazovanja programObrazovanja) {
        this.programObrazovanja = programObrazovanja;
    }

    @Override
    public String toString() {
        return "Upis{" +
                "id=" + id +
                ", polaznik=" + polaznik.getIme() + " ,prezime " + polaznik.getPrezime() +
                ", programObrazovanja=" + programObrazovanja.getNaziv() + " ,CSVET" + programObrazovanja.getCSVET() +
                '}';
    }
}
