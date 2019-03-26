package com.example.uploadservice.model;

import javax.persistence.*;

@Entity
public class Ucenik{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String ime;
    private String prezime;


    public Ucenik() {}

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

    public Ucenik(String ime, String prezime){
        this.ime = ime;
        this.prezime = prezime;
    }
}