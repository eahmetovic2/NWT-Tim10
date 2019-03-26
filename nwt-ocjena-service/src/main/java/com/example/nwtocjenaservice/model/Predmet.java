package com.example.nwtocjenaservice.model;
import javax.persistence.*;

@Entity
public class Predmet{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    public String naziv;
    public Integer fkNastavnikId;


    public Predmet() {}

    public String getPredmet() {
        return naziv;
    }

    public void setPredmet(String naziv) {
        this.naziv = naziv;
    }

    public Predmet(String naziv, Integer fkNastavnikId){
        this.naziv = naziv;
        this.fkNastavnikId = fkNastavnikId;
    }
}