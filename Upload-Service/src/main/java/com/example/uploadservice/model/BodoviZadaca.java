package com.example.uploadservice.model;

import com.example.uploadservice.model.Ucenik;
import com.example.uploadservice.model.Zadaca;
import javax.persistence.*;

@Entity
public class BodoviZadaca{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Zadaca.class)
    @JoinColumn(name = "zadaca_id", nullable = false)
    private Zadaca zadaca;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ucenik.class)
    @JoinColumn(name = "ucenik_id", nullable = false)
    private Ucenik ucenik;

    @Column(name="bodovi")
    private Integer bodovi;


    public Zadaca getZadaca() {
        return zadaca;
    }

    public void setZadaca(Zadaca zadaca) {
        this.zadaca = zadaca;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public void setUcenik(Ucenik ucenik) {
        this.ucenik = ucenik;
    }

    public Integer getBodovi() {
        return bodovi;
    }

    public void setBodovi(Integer bodovi) {
        this.bodovi = bodovi;
    }

    public BodoviZadaca() {}

    public BodoviZadaca(Zadaca zadaca, Ucenik ucenik, Integer bodovi){
        this.zadaca = zadaca;
        this.ucenik = ucenik;
        this.bodovi = bodovi;
    }
}