package com.example.nwtizostanakservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.nwtizostanakservice.model.Ucenik;
import com.example.nwtizostanakservice.service.UcenikService;
import com.example.nwtizostanakservice.model.Izostanak;
import com.example.nwtizostanakservice.service.IzostanakService;
import com.example.nwtizostanakservice.model.Predmet;
import com.example.nwtizostanakservice.service.PredmetService;
import com.example.nwtizostanakservice.proxy.IzostanakOcjenaServiceProxy;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import javax.validation.ConstraintViolationException;
import javax.validation.ConstraintViolation;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import java.sql.Date;

@RestController
public class DefaultController {

    @Autowired
    private UcenikService ucenikService;

    @Autowired
    private PredmetService predmetService;

    @Autowired
    private IzostanakService izostanakService;

    @Autowired
    private IzostanakOcjenaServiceProxy ocjenaProxy;



    @RequestMapping(value="/pocetna", method = RequestMethod.GET)
    public void pocetna() { 

        Ucenik ucenik = new Ucenik("John","Doe");
        Ucenik ucenik2 = ucenikService.save(ucenik);

        Predmet predmet = new Predmet("likovno");
        predmetService.save(predmet);

        Date k = new Date(2011, 22, 9);
        Izostanak i = new Izostanak(k,predmet,ucenik, true);
        izostanakService.save(i);

        //Testni slucajevi;

        //ucenik = new Ucenik("John4","Doe");
        //ucenik2 = ucenikService.save(ucenik);

        //predmet = new Predmet("likovno");
        //predmetService.save(predmet);

        //k = new Date(2011, 22, 9);
        //i = new Izostanak(k,predmet,ucenik);
        //izostanakService.save(i);

    }

    // ---> Get All Predmet - GET <---
    @RequestMapping(value="/ocjene", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllOcjene() { 
        return ocjenaProxy.dajSveOcjene();		
    }

    
}