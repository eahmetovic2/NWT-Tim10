package com.example.nwtocjenaservice.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.time.LocalDate;

@Entity
@Table(name="ocjena")
public class Ocjena{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="ocjena")
    @Max(value = 10, message="Ocjena mora biti manja ili jednaka 10")
    @Min(value = 1, message="ocjena mora biti veća ili jednaka 0")
    public Integer ocjena;

    @Column(name="datum")
    public LocalDate datum;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ucenik.class)
    @JoinColumn(name = "ucenik_id", nullable = false)
    private Ucenik ucenik;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Predmet.class)
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;
    
    public Ocjena() {
    }

    public Ocjena(Integer id, Integer ocjena, LocalDate datum, Ucenik ucenik, Predmet predmet) {
        this.id = id;
        this.ocjena = ocjena;
        this.datum = datum;
        this.ucenik = ucenik;
        this.predmet = predmet;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOcjena() {
        return this.ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
    }

    public LocalDate getDatum() {
        return this.datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Ucenik getUcenik() {
        return this.ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }

    public Predmet getPredmet() {
        return this.predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", ocjena='" + getOcjena() + "'" +
            ", datum='" + getDatum() + "'" +
            ", ucenik='" + getUcenik() + "'" +
            ", predmet='" + getPredmet() + "'" +
            "}";
    }

}