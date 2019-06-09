package com.example.nwtizostanakservice.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="izostanak")
public class Izostanak{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="datum")
    public Date datum;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Predmet.class)
    @JoinColumn(name = "predmetid", nullable = false)
    @NotNull(message = "Predmet ne smije biti null")
    public Predmet predmet;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ucenik.class)
    @JoinColumn(name = "ucenikid", nullable = false)
    @NotNull(message = "Ucenik ne smije biti null")
    public Ucenik ucenik;

    @Column(name="opravdan")
    public boolean opravdan;
    
    @Column(name="prisutan")
    public boolean prisutan;


    public Izostanak() {}

    public Date getDatum() {
        return datum;
    }

    public Predmet getPredmet() {
        return this.predmet;
    }

    public Ucenik getUcenik() {
        return this.ucenik;
    }

    public boolean getOpravdanje() {
        return opravdan;
    }
    
    public void setOpravdanje(boolean opravdan) {
        this.opravdan=opravdan;
    }

    public boolean getPrisutan() {
        return prisutan;
    }
    
    public void setPrisutan(boolean prisutan) {
        this.prisutan=prisutan;
    }

    public Izostanak(Date datum, Predmet predmet, Ucenik ucenik, boolean prisutan){
        this.datum = datum;
        this.predmet=predmet;
        this.ucenik=ucenik;
        this.opravdan=false;
        this.prisutan=prisutan;
    }

    
    @Override
    public String toString() {
        return "{" +
            " date='" + getDatum() + "'" +
            ", predmetid='" + getPredmet() + "'" +
            ", ucenikid='" + getUcenik() + "'" +
            ", opravdan='" + getOpravdanje() + "'" +
            ", prisutan='" + getPrisutan() + "'" +
            "}";
    }
}