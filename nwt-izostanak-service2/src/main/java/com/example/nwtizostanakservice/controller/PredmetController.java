package com.example.nwtizostanakservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.nwtizostanakservice.model.Predmet;
import com.example.nwtizostanakservice.service.PredmetService;

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

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

import jdk.incubator.http.internal.common.Log;

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
public class PredmetController {

    @Autowired
    private PredmetService predmetService;

    @RequestMapping(value="/predmet/sve", method = RequestMethod.GET)
    public List<Predmet> dajSvePredmete() { 
        return predmetService.dajSvePredmete();
    }

    @RequestMapping(value="/predmet/{predmetid}", method = RequestMethod.GET)
    public Optional<Predmet> getPredmetById(@PathVariable Integer predmetid) { 
        return predmetService.getPredmetById(predmetid);
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

    @RequestMapping(value = "/predmet/create", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Object> create(@RequestBody Predmet model) throws Exception  { 
        Predmet p;
        try {

            p =new Predmet(model.naziv);
        }catch(Error e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nevalidan naziv predmeta");
        
        }
        return ResponseEntity.ok(predmetService.save(p));
    }
}