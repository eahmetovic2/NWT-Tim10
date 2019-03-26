package com.example.nwtocjenaservice.model;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Ocjena{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    public Integer ocjena;
    public Integer fkUcenikId;
    public Integer fkPredmetId;
    public LocalDate datum;


    public Ocjena() {}

    public Integer getOcjena() {
        return ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
    }


    public Ocjena(Integer ocjena, LocalDate datum, Integer ucenikId, Integer predmetId){
        this.ocjena = ocjena;
        this.fkUcenikId = ucenikId;
        this.fkPredmetId = predmetId;
        this.datum = datum;
    }
}