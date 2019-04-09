package com.example.uploadservice.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "predmet")
public class Predmet{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Naziv predmeta ne moze biti prazno")
    @Size(min=2, max=30, message= "Naziv predmeta mora imati vise od 3 slova i manje od 30")
    private String naziv;

    @Transient
    private Integer nastavnikId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    public Integer getNastavnikId() {
        return this.nastavnikId;
    }

    public void setNastavnikId(Integer nastavnikId) {
        this.nastavnikId = nastavnikId;
    }
    
    public Predmet() {}

    public Predmet(String naziv){
        this.naziv = naziv;
    }   

    public Predmet(String naziv, Integer nastavnikId){
        this.naziv = naziv;
        this.nastavnikId = nastavnikId;
    }
}