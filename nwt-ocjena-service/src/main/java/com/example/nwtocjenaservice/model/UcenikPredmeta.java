package com.example.nwtocjenaservice.model;
import javax.persistence.*;

@Entity
@Table(name="ucenikpredmeta")
public class UcenikPredmeta{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ucenik.class)
    @JoinColumn(name = "ucenik_id", nullable = false)
    private Ucenik ucenik;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Predmet.class)
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;


    public UcenikPredmeta() {
    }

    public UcenikPredmeta(Integer id, Ucenik ucenik, Predmet predmet) {
        this.id = id;
        this.ucenik = ucenik;
        this.predmet = predmet;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ucenik getUcenik() {
        return this.ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }

    public Predmet getPredmet() {
        return this.predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
    
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", ucenik='" + getUcenik() + "'" +
            ", predmet='" + getPredmet() + "'" +
            "}";
    }

}