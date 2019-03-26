package com.example.nwtocjenaservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.nwtocjenaservice.model.Ocjena;
import com.example.nwtocjenaservice.service.OcjenaService;

import com.example.nwtocjenaservice.model.Predmet;
import com.example.nwtocjenaservice.service.PredmetService;

import com.example.nwtocjenaservice.model.Ucenik;
import com.example.nwtocjenaservice.service.UcenikService;

import com.example.nwtocjenaservice.model.Nastavnik;
import com.example.nwtocjenaservice.service.NastavnikService;

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

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


@RestController
public class DefaultController {

    @Autowired
    private OcjenaService ocjenaService;

    @Autowired
    private UcenikService ucenikService;

    @Autowired
    private NastavnikService nastavnikService;

    @Autowired
    private PredmetService predmetService;

    @RequestMapping(value="/testAllModels", method = RequestMethod.GET)
    public void testAllModels() { 

        Ucenik ucenik = new Ucenik("John","Doe");
        Ucenik ucenik2 = ucenikService.save(ucenik);

        Nastavnik nastavnik = new Nastavnik("Hamza","Iseric");
        Nastavnik nastavnik2 = nastavnikService.save(nastavnik);

        Predmet predmet = new Predmet("IM2", 1);
        Predmet predmet2 = predmetService.save(predmet);

        LocalDate myDateObj = LocalDate.now();

        Ocjena ocjena = new Ocjena(10, myDateObj, 1, 1);
        Ocjena ocjena2 = ocjenaService.save(ocjena);
    }
}