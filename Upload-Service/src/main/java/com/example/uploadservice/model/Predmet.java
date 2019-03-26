package com.example.uploadservice.model;

import javax.persistence.*;

@Entity
public class Predmet{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String naziv;


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
    public Predmet() {}

    public Predmet(String naziv){
        this.naziv = naziv;
    }   
}