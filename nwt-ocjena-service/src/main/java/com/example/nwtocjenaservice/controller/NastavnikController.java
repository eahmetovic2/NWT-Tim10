package com.example.nwtocjenaservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.nwtocjenaservice.model.Nastavnik;
import com.example.nwtocjenaservice.service.NastavnikService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class NastavnikController {

    @Autowired
    private NastavnikService nastavnikService;

    @RequestMapping(value="/nastavnik/sve", method = RequestMethod.GET)
    public List<Nastavnik> dajSveNastavnike() { 
        return nastavnikService.dajSveNastavnike();
    }

    @RequestMapping(value="/nastavnik/{nastavnikId}", method = RequestMethod.GET)
    public Optional<Nastavnik> getNastavnikById(@PathVariable Integer nastavnikId) { 
        return nastavnikService.getNastavnikById(nastavnikId);
    }

    @RequestMapping(value = "/nastavnik/create", method = RequestMethod.POST, consumes="application/json")
    public Nastavnik create(@Valid @RequestBody Nastavnik model) { 
        
        return nastavnikService.save(model);
    }


}