package com.example.nwtocjenaservice.controller;

import com.example.nwtocjenaservice.model.Nastavnik;
import com.example.nwtocjenaservice.model.Predmet;
import com.example.nwtocjenaservice.model.request.PredmetRequest;
import com.example.nwtocjenaservice.service.NastavnikService;
import com.example.nwtocjenaservice.service.PredmetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.*;
import java.util.Optional;

import javax.validation.Valid;


@RestController
public class PredmetController {

    @Autowired
    private PredmetService predmetService;

    @Autowired
    private NastavnikService nastavnikService;

    @RequestMapping(value="/predmet/sve", method = RequestMethod.GET)
    public List<Predmet> dajSvePredmete() { 
        return predmetService.dajSvePredmete();
    }

    @RequestMapping(value="/predmet/{predmetId}", method = RequestMethod.GET)
    public Optional<Predmet> getPredmetById(@PathVariable Integer predmetId) { 
        return predmetService.getPredmetById(predmetId);
    }

    @RequestMapping(value = "/predmet/create", method = RequestMethod.POST, consumes="application/json")
    public Predmet create(@Valid @RequestBody PredmetRequest model) throws Exception {
        if(model.nastavnik_id == null) {
            throw new Exception("Nastavnik id mora imati neku vrijednost");
        }
        Nastavnik nastavnik;
        try {
            nastavnik = nastavnikService.getNastavnikById(model.nastavnik_id).get();
        } catch(Exception e) {
            throw new Exception("Ne postoji nastavnik sa tim id-om");
        }
        Predmet predmet = new Predmet(0, model.naziv, nastavnik);
        return predmetService.save(predmet);
    }
}