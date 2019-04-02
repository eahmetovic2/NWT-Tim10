package com.example.nwtocjenaservice.controller;

import com.example.nwtocjenaservice.model.Ocjena;
import com.example.nwtocjenaservice.model.Predmet;
import com.example.nwtocjenaservice.model.Ucenik;
import com.example.nwtocjenaservice.model.request.OcjenaRequest;
import com.example.nwtocjenaservice.service.OcjenaService;
import com.example.nwtocjenaservice.service.PredmetService;
import com.example.nwtocjenaservice.service.UcenikService;

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
public class OcjenaController {

    @Autowired
    private OcjenaService ocjenaService;
    @Autowired
    private UcenikService ucenikService;
    @Autowired
    private PredmetService predmetService;

    @RequestMapping(value="/ocjena/sve", method = RequestMethod.GET)
    public List<Ocjena> dajSveOcjene() { 
        return ocjenaService.dajSveOcjene();
    }

    @RequestMapping(value="/ocjena/ucenik/{ucenikId}", method = RequestMethod.GET)
    public List<Ocjena> dajSveOcjene(@PathVariable Integer ucenikId) { 
        return ocjenaService.dajSveOcjeneUcenika(ucenikId);
    }

    @RequestMapping(value="/ocjena/ucenik/{ucenikId}/predmet/{predmetId}", method = RequestMethod.GET)
    public List<Ocjena> dajSveOcjeneUcenikaPredmeta(@PathVariable Integer ucenikId, @PathVariable Integer predmetId) throws Exception { 
        
        return ocjenaService.dajSveOcjeneUcenikaPredmeta(ucenikId, predmetId);
    }

    @RequestMapping(value="/ocjena/{ocjenaId}", method = RequestMethod.GET)
    public Optional<Ocjena> getOcjenaById(@PathVariable Integer ocjenaId) { 
        return ocjenaService.getOcjenaById(ocjenaId);
    }

    @RequestMapping(value = "/ocjena/create", method = RequestMethod.POST, consumes="application/json")
    public Ocjena create(@Valid @RequestBody OcjenaRequest model) throws Exception {
        if(model.ucenik_id == null) {
            throw new Exception("Ucenik id mora imati neku vrijednost");
        }
        if(model.predmet_id == null) {
            throw new Exception("Predmet id mora imati neku vrijednost");
        }
        Ucenik ucenik;
        try {
            ucenik = ucenikService.getUcenikById(model.ucenik_id).get(); 
        } catch(Exception e) {
            throw new Exception("Ne postoji ucenik sa tim id-om");
        }

        Predmet predmet;
        try {
            predmet = predmetService.getPredmetById(model.predmet_id).get();
        } catch(Exception e) {
            throw new Exception("Ne postoji predmet sa tim id-om");
        }
        Ocjena ocjena = new Ocjena(0, model.ocjena, model.datum, ucenik, predmet);
        return ocjenaService.save(ocjena);
    }

}