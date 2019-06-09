package com.example.nwtocjenaservice.controller;

import com.example.nwtocjenaservice.model.Predmet;
import com.example.nwtocjenaservice.model.Ucenik;
import com.example.nwtocjenaservice.model.UcenikPredmeta;
import com.example.nwtocjenaservice.model.request.UcenikPredmetaRequest;
import com.example.nwtocjenaservice.service.PredmetService;
import com.example.nwtocjenaservice.service.UcenikPredmetaService;
import com.example.nwtocjenaservice.service.UcenikService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.*;
import java.util.Optional;

@RestController
public class UcenikPredmetaController {

    @Autowired
    private UcenikPredmetaService ucenikPredmetaService;
    @Autowired
    private UcenikService ucenikService;
    @Autowired
    private PredmetService predmetService;

    @RequestMapping(value="/ucenik-predmeta/predmet/{predmetId}", method = RequestMethod.GET)
    public List<UcenikPredmeta> dajSveUcenikePredmeta(@PathVariable Integer predmetId) { 
        //return ucenikPredmetaService.dajSvePredmeteUcenika(predmetId);
        return ucenikPredmetaService.dajSveUcenikePredmeta(predmetId);//ispravno
    }

    @RequestMapping(value="/ucenik-predmeta/{ucenikPredmetaId}", method = RequestMethod.GET)
    public Optional<UcenikPredmeta> getUcenikPredmetaById(@PathVariable Integer ucenikPredmetaId) { 
        
        //return ucenikPredmetaService.dajSvePredmeteUcenika(ucenikId);
        return ucenikPredmetaService.getUcenikPredmetaById(ucenikPredmetaId);//ispravno
    }
    
    
    @RequestMapping(value="/ucenik-predmeta/ucenik/{ucenikId}", method = RequestMethod.GET)
    public List<UcenikPredmeta> dajSvePredmeteUcenika(@PathVariable Integer ucenikId) {
        return ucenikPredmetaService.dajSvePredmeteUcenika(ucenikId);
    }

    @RequestMapping(value = "/ucenik-predmeta/create", method = RequestMethod.POST, consumes="application/json")
    public UcenikPredmeta create(@RequestBody UcenikPredmetaRequest model) throws Exception {
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
        UcenikPredmeta ucenikPredmeta = new UcenikPredmeta(0, ucenik, predmet);
        return ucenikPredmetaService.save(ucenikPredmeta);
    }

    

}