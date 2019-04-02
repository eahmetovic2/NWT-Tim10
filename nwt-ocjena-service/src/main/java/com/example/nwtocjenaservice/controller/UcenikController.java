package com.example.nwtocjenaservice.controller;

import com.example.nwtocjenaservice.model.Ucenik;
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
public class UcenikController {

    @Autowired
    private UcenikService ucenikService;

    @RequestMapping(value="/ucenik/sve", method = RequestMethod.GET)
    public List<Ucenik> dajSveUcenike() { 
        return ucenikService.dajSveUcenike();
    }

    @RequestMapping(value="/ucenik/{ucenikId}", method = RequestMethod.GET)
    public Optional<Ucenik> getUcenikById(@PathVariable Integer ucenikId) { 
        return ucenikService.getUcenikById(ucenikId);
    }

    @RequestMapping(value = "/ucenik/create", method = RequestMethod.POST, consumes="application/json")
    public Ucenik create(@Valid @RequestBody Ucenik model) {         
        return ucenikService.save(model);
    }
}