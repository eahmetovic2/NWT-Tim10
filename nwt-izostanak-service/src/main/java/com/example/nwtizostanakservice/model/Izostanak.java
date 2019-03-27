package com.example.nwtizostanakservice.model;
import java.sql.Date;

import javax.persistence.*;

@Entity
public class Izostanak{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    public Date datum;
    public Integer predmetid;
    public Integer ucenikid;
    private boolean opravdan;


    public Izostanak() {}

    public Date getDatum() {
        return datum;
    }

    public Integer getPredmetid() {
        return predmetid;
    }

    public Integer getUcenikid() {
        return ucenikid;
    }

    public boolean getOpravdanje() {
        return opravdan;
    }
    
    public void setOpravdanje(boolean opravdan) {
        this.opravdan=opravdan;
    }

    public Izostanak(Date datum, Integer predmetid, Integer ucenikid){
        this.datum = datum;
        this.predmetid=predmetid;
        this.ucenikid=ucenikid;
        this.opravdan=false;
    }
}