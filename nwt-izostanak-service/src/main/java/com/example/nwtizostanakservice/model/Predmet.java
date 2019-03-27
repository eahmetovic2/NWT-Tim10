package com.example.nwtizostanakservice.model;
import javax.persistence.*;

@Entity
public class Predmet{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    public String naziv;


    public Predmet() {}

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