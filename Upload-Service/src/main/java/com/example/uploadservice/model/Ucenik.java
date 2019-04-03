package com.example.uploadservice.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "ucenik")
public class Ucenik{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Ime ucenika ne moze biti prazno.")
    @Size(min=3, max=30, message= "Ime ucenika mora imati vise od 3 slova i manje od 30.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Samo su slova dozvoljena za ime ucenika.")
    private String ime;

    @NotNull(message = "Prezime ucenika ne moze biti prazno.")
    @Size(min=3, max=30, message= "Prezime ucenika mora imati vise od 3 slova i manje od 30.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Samo su slova dozvoljena za prezime ucenika.")
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