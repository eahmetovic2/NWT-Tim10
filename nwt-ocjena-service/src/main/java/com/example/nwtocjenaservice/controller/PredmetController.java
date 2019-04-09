package com.example.nwtocjenaservice.controller;

import com.example.nwtocjenaservice.model.Nastavnik;
import com.example.nwtocjenaservice.model.Predmet;
import com.example.nwtocjenaservice.model.Greska;
import com.example.nwtocjenaservice.model.request.PredmetRequest;
import com.example.nwtocjenaservice.service.NastavnikService;
import com.example.nwtocjenaservice.service.PredmetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;
import java.util.*;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ConstraintViolationException;
import javax.validation.ConstraintViolation;



@RestController
public class PredmetController {

    @Autowired
    private PredmetService predmetService;

    @Autowired
    private NastavnikService nastavnikService;


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstaintViolatoinException(final ConstraintViolationException ex) {

        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            message.append(violation.getMessage().concat(";"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska(message.toString())); 
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException(final NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("provjerite ids koje ste poslali!")); 
    }

    // ---> Create Predmet - POST <---
    @RequestMapping(value="/predmet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> savePredmet(@RequestBody Predmet predmet) { 
        predmet.setNastavnik(nastavnikService.getNastavnikById(predmet.getNastavnikId()).get());
        Predmet predmetData = predmetService.save(predmet);
        return ResponseEntity.ok(predmetData);
    }

    // ---> Update Predmet - PUT <---
    @RequestMapping(value="/predmet/{predmetId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePredmet(@RequestBody Predmet predmetNovi, @PathVariable Integer predmetId) { 
        Predmet predmet = null;
        try {
            predmet = predmetService.getPredmetById(predmetId).get();
            predmet.setNaziv(predmetNovi.getNaziv());
            predmet.setNastavnik(nastavnikService.getNastavnikById(predmetNovi.getNastavnikId()).get());
            predmet = predmetService.save(predmet);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("Ne postoji predmet sa trazenim id-om."));
        }
        return ResponseEntity.ok(predmet);
    }


    // ---> Delete Predmet - DELETE <---
    @RequestMapping(value="/predmet/{predmetId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deletePredmet(@PathVariable Integer predmetId) { 
        Predmet predmet = null;
        try {
            predmet = predmetService.getPredmetById(predmetId).get();
            predmetService.delete(predmet);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("Ne postoji predmet sa trazenim id-om."));
        }
        return ResponseEntity.ok(predmet);
    }


    // ---> Get Predmet - GET <---
    @RequestMapping(value="/predmet/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPredmet(@PathVariable Integer id) { 
        Predmet predmet = null;
        try {
            predmet = predmetService.getPredmetById(id).get();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("Ne postoji predmet sa trazenim id-om."));
        }
        return ResponseEntity.ok(predmet);
    }


    // ---> Get All Predmet - GET <---
    @RequestMapping(value="/predmeti", method = RequestMethod.GET)
    public List<Predmet> getAllPredmet() { 
        return predmetService.dajSvePredmete();		
    }
}