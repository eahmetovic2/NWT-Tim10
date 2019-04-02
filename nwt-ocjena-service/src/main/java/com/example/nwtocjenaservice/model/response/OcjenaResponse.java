package com.example.nwtocjenaservice.model.response;

import com.example.nwtocjenaservice.model.Predmet;
import com.example.nwtocjenaservice.model.Ucenik;

import java.time.LocalDate;

public class OcjenaResponse{
    public Integer ocjena;

    public LocalDate datum;

    private Ucenik ucenik;

    private Predmet predmet;

}