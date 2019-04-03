package com.example.uploadservice.model;

import com.example.uploadservice.model.Ucenik;
import com.example.uploadservice.model.Zadaca;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "bodovi_zadaca")
public class BodoviZadaca{

    @Transient
    private Integer ucenikId;

    @Transient
    private Integer zadacaId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Zadaca.class)
    @JoinColumn(name = "zadaca_id", nullable = false)
    private Zadaca zadaca;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ucenik.class)
    @JoinColumn(name = "ucenik_id", nullable = false)
    private Ucenik ucenik;

    @Min(value=0, message= "Broj bodova za odredenu zadacu mora biti izmedu 1 i 10.")
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

    public Integer getZadacaId() {
        return zadacaId;
    }

    public void setZadacaId(Integer zadacaId) {
        this.zadacaId = zadacaId;
    }

    public Integer getUcenikId() {
        return ucenikId;
    }

    public void setUcenikId(Integer ucenikId) {
        this.ucenikId = ucenikId;
    }

    public BodoviZadaca() {}
    public BodoviZadaca(Integer zadacaId, Integer ucenikId, Integer bodovi) {
        this.zadacaId = zadacaId;
        this.ucenikId = ucenikId;
        this.bodovi = bodovi;
    }
    public BodoviZadaca(Zadaca zadaca, Ucenik ucenik, Integer bodovi){
        this.zadaca = zadaca;
        this.ucenik = ucenik;
        this.bodovi = bodovi;
    }
}