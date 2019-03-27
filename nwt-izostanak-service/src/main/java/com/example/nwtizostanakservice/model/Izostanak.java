package com.example.nwtizostanakservice.model;
import java.sql.Date;

import javax.persistence.*;

@Entity
public class Izostanak{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    public Date datum;
    public Integer predmetId;
    public Integer ucenikId;
    private boolean opravdan;


    public Izostanak() {}

    public Date getDatum() {
        return datum;
    }

    public Integer getPredmetId() {
        return predmetId;
    }

    public Integer getUcenikId() {
        return ucenikId;
    }

    public boolean getOpravdanje() {
        return opravdan;
    }
    
    public void setOpravdanje(boolean opravdan) {
        this.opravdan=opravdan;
    }

    public Izostanak(Date datum, Integer predmetId, Integer ucenikId){
        this.datum = datum;
        this.predmetId=predmetId;
        this.ucenikId=ucenikId;
        this.opravdan=false;
    }
}