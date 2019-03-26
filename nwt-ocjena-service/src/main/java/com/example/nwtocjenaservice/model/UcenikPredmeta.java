package com.example.nwtocjenaservice.model;
import javax.persistence.*;

@Entity
public class UcenikPredmeta{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer fkUcenikId;
    public Integer fkPredmetId;


    public UcenikPredmeta() {}

    public Integer getUcenikId() {
        return fkUcenikId;
    }

    public void setUcenikId(Integer fkUcenikId) {
        this.fkUcenikId = fkUcenikId;
    }

    public Integer getPredmetId() {
        return fkPredmetId;
    }

    public void setgetPredmetId(Integer fkPredmetId) {
        this.fkPredmetId = fkPredmetId;
    }

    public UcenikPredmeta(Integer fkUcenikId, Integer fkPredmetId){
        this.fkUcenikId = fkUcenikId;
        this.fkPredmetId = fkPredmetId;
    }
}