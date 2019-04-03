package com.example.nwtizostanakservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="ucenik")
public class Ucenik{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="ime")
    @NotBlank(message = "Morate unijeti ime ucenika")
    @Size(min = 3, max = 50, message = "Ime mora biti duze od 3 karaktera, a amnje od 50")
    @Pattern(regexp="^*(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message="Ime ne smije sadrzavati brojeve")
    @Pattern(regexp="[A-Z][a-z0-9]*$", message="Ime mora počinjati velikim slovom")
    private String ime;
//@Pattern(regexp="^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message="Ime mora počinjati velikim slovom")
    @Column(name="prezime")
    @NotBlank(message = "Morate unijeti prezime ucenika")
    @Size(min = 3, max = 50, message = "Prezime mora biti duze od 3 karaktera, a amnje od 50")
    @Pattern(regexp="^*(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message="Prezime ne smije sadrzavati brojeve")
    @Pattern(regexp="^[A-Z][a-z0-9]*$", message="Prezime mora počinjati velikim slovom")
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