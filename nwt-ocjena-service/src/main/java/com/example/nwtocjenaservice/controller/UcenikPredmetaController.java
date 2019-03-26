package com.example.nwtocjenaservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.nwtocjenaservice.model.UcenikPredmeta;
import com.example.nwtocjenaservice.service.UcenikPredmetaService;

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
public class UcenikPredmetaController {

    @Autowired
    private UcenikPredmetaService ucenikPredmetaService;

    @RequestMapping(value="/ucenik-predmeta/predmet/{predmetId}", method = RequestMethod.GET)
    public List<UcenikPredmeta> dajSveUcenikePredmeta(@PathVariable Integer predmetId) { 
        return ucenikPredmetaService.dajSveUcenikePredmeta(predmetId);
    }

    @RequestMapping(value="/ucenik-predmeta/{ucenikPredmetaId}", method = RequestMethod.GET)
    public Optional<UcenikPredmeta> getUcenikPredmetaById(@PathVariable Integer ucenikPredmetaId) { 
        return ucenikPredmetaService.getUcenikPredmetaById(ucenikPredmetaId);
    }

    @RequestMapping(value = "/ucenik-predmeta/create", method = RequestMethod.POST, consumes="application/json")
    public UcenikPredmeta create(@RequestBody UcenikPredmeta model) { 
        UcenikPredmeta uc = new UcenikPredmeta(model.fkUcenikId, model.fkPredmetId);
        System.out.println(uc.fkUcenikId);
        System.out.println(uc.fkPredmetId);
        return ucenikPredmetaService.save(uc);
    }
}