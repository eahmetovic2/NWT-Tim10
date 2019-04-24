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

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Nastavnik.class)
    @JoinColumn(name = "nastavnik_id", nullable = true)
    private Nastavnik nastavnik;

    @Transient
    private Integer nastavnikId;


    public Predmet() {
    }



    public Predmet(Integer id, String naziv, Nastavnik nastavnik) {
        this.id = id;
        this.naziv = naziv;
        this.nastavnik = nastavnik;
    }

    public Predmet(String naziv, Integer nastavnikId){
        this.naziv = naziv;
        this.nastavnikId = nastavnikId;
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

     public Integer getNastavnikId() {
        return this.nastavnikId;
    }

    public void setNastavnikId(Integer nastavnikId) {
        this.nastavnikId = nastavnikId;
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