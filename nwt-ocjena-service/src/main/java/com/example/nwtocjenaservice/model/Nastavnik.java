package com.example.nwtocjenaservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="nastavnik")
public class Nastavnik{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="ime")
    @NotBlank(message = "Morate unijeti ime")
    public String ime;

    @NotBlank(message = "Morate unijeti prezime")
    @Column(name="prezime")
    public String prezime;


    public Nastavnik() {
    }

    public Nastavnik(Integer id, String ime, String prezime) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return this.ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return this.prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", ime='" + getIme() + "'" +
            ", prezime='" + getPrezime() + "'" +
            "}";
    }

}