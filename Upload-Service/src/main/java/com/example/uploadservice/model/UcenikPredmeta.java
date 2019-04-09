package com.example.uploadservice.model;

import com.example.uploadservice.model.Ucenik;
import com.example.uploadservice.model.Predmet;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "ucenik_predmeta")
public class UcenikPredmeta{

    @Transient
    private Integer ucenikId;

    @Transient
    private Integer predmetId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Predmet.class)
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ucenik.class)
    @JoinColumn(name = "ucenik_id", nullable = false)
    private Ucenik ucenik;


    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }


    public Integer getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Integer predmetId) {
        this.predmetId = predmetId;
    }

    public Integer getUcenikId() {
        return ucenikId;
    }

    public void setUcenikId(Integer ucenikId) {
        this.ucenikId = ucenikId;
    }

    public UcenikPredmeta() {}
    
    public UcenikPredmeta(Integer predmetId, Integer ucenikId) {
        this.predmetId = predmetId;
        this.ucenikId = ucenikId;
    }
     public UcenikPredmeta(Ucenik ucenik, Predmet predmet) {
        this.ucenik = ucenik;
        this.predmet = predmet;
    }
}