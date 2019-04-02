package com.example.nwtocjenaservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="predmet")
public class Predmet{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="naziv")
    @NotBlank(message = "Morate unijeti naziv")
    private String naziv;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Nastavnik.class)
    @JoinColumn(name = "nastavnik_id", nullable = false)
    private Nastavnik nastavnik;


    public Predmet() {
    }

    public Predmet(Integer id, String naziv, Nastavnik nastavnik) {
        this.id = id;
        this.naziv = naziv;
        this.nastavnik = nastavnik;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return this.naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Nastavnik getNastavnik() {
        return this.nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", naziv='" + getNaziv() + "'" +
            ", nastavnik='" + getNastavnik() + "'" +
            "}";
    }

}