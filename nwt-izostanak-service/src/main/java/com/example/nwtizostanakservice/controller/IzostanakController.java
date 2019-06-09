package com.example.nwtizostanakservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.nwtizostanakservice.model.Izostanak;
import com.example.nwtizostanakservice.model.Predmet;
import com.example.nwtizostanakservice.model.Ucenik;
import com.example.nwtizostanakservice.model.request.IzostanakRequest;
import com.example.nwtizostanakservice.service.IzostanakService;
import com.example.nwtizostanakservice.service.PredmetService;
import com.example.nwtizostanakservice.service.UcenikService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;



@RestController
public class IzostanakController {

    @Autowired
    private IzostanakService izostanakService;

    @Autowired
    private UcenikService ucenikService;

    @Autowired
    private PredmetService predmetService;

    @RequestMapping(value="/izostanak/sve", method = RequestMethod.GET)
    public List<Izostanak> dajSveIzostanake() { 
        return izostanakService.dajSveIzostanke();
    }

    @RequestMapping(value="/izostanak/{izostanak_id}", method = RequestMethod.GET)
    public Optional<Izostanak> getIzostanakById(@PathVariable Integer izostanak_id) { 
        return izostanakService.getIzostanakById(izostanak_id);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstaintViolatoinException(final ConstraintViolationException ex) {

        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            message.append(violation.getMessage().concat(";"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message); 
    }

    @RequestMapping(value = "/izostanak/create", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Object> create(@Valid @RequestBody IzostanakRequest model) throws Exception {
        if(model.ucenikid == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ucenik id mora imati neku vrijednost");
        
        }
        if(model.predmetid == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Predmet id mora imati neku vrijednost");
        
        }
        Ucenik ucenik;
        try {
            ucenik = ucenikService.getUcenikById(model.ucenikid).get(); 
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ne postoji ucenik sa tim id-om");
        
        }

        Predmet predmet;
        try {
            predmet = predmetService.getPredmetById(model.predmetid).get();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ne postoji predmet sa tim id-om");
        
        }
        Izostanak i;
        try {
            i=izostanakService.save(new Izostanak(model.datum,predmet,ucenik, model.prisutan));

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nemoguce kreirati takav izostanak");
        
        }
        return ResponseEntity.ok(i);
    }

    @RequestMapping(value="/izostanak/ucenik/{ucenikId}/predmet/{predmetId}", method = RequestMethod.GET)
    public List<Izostanak> vratiIzostankeUcenikaPredmeta(@PathVariable Integer ucenikId, @PathVariable Integer predmetId) throws Exception { 
        return izostanakService.dajSveIzostankeUcenikaPredmeta(ucenikId, predmetId);
    }

}