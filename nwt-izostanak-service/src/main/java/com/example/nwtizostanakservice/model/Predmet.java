package com.example.nwtizostanakservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="predmet")
public class Predmet{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="naziv")
    @NotBlank(message = "Morate unijeti naziv predmeta")
    public String naziv;


    public Predmet() {}

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNazv(String ime) {
        this.naziv = ime;
    }

    public Predmet(String naziv){
        this.naziv = naziv;
    }
}