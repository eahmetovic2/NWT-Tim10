package com.example.nwtizostanakservice.model;

public class Greska{


    private String poruka;


    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
    
    
    public Greska() {}

    public Greska(String poruka){
        this.poruka = poruka;
    }   
}