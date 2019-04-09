package com.example.uploadservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.uploadservice.model.Greska;
import com.example.uploadservice.proxy.UploadIzostanakServiceProxySimple;

import com.example.uploadservice.model.Zadaca;
import com.example.uploadservice.service.ZadacaService;

import com.example.uploadservice.model.Predmet;
import com.example.uploadservice.service.PredmetService;

import com.example.uploadservice.model.Ucenik;
import com.example.uploadservice.service.UcenikService;

import com.example.uploadservice.model.BodoviZadaca;
import com.example.uploadservice.service.BodoviZadacaService;

import com.example.uploadservice.model.UcenikPredmeta;
import com.example.uploadservice.service.UcenikPredmetaService;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.validation.ConstraintViolationException;
import javax.validation.ConstraintViolation;

@RestController
public class DefaultController {

    @Autowired
    private BodoviZadacaService bodoviZadacaService;

	@Autowired
    private PredmetService predmetService;

    @Autowired
    private UcenikService ucenikService;

    @Autowired
    private ZadacaService zadacaService;

    @Autowired
    private UcenikPredmetaService ucenikPredmetaService;

    @Autowired
	private UploadIzostanakServiceProxySimple simpleProxy;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstaintViolatoinException(final ConstraintViolationException ex) {

        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            message.append(violation.getMessage().concat(";"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska(message.toString())); 
    }


    // @RequestMapping(value="/testAllModels", method = RequestMethod.GET)
    // public void testAllModels() { 
    //     Ucenik ucenik = new Ucenik("John","Doe");
    //     ucenikService.save(ucenik);
    //     Predmet predmet = new Predmet("IM2");
    //     predmetService.save(predmet);
    //     Zadaca zadaca = new Zadaca("open",predmet,"fn","fi","ft","wbl","wvl");
    //     zadacaService.save(zadaca);
    //     BodoviZadaca bodoviZadaca = new BodoviZadaca(zadaca,ucenik,3);
    //     bodoviZadacaService.save(bodoviZadaca); 
    // }


    // -------------> Predmet <-------------


    // ---> Create Predmet - POST <---
    @RequestMapping(value="/predmet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> savePredmet(@RequestBody Predmet predmet) { 
        return simpleProxy.savePredmet(predmet);
    }

    // ---> Update Predmet - PUT <---
    @RequestMapping(value="/predmet/{predmetId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePredmet(@RequestBody Predmet predmetNovi, @PathVariable Integer predmetId) { 
        return simpleProxy.updatePredmet(predmetNovi,predmetId);
    }


    // ---> Delete Predmet - DELETE <---
    @RequestMapping(value="/predmet/{predmetId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deletePredmet(@PathVariable Integer predmetId) { 
        return simpleProxy.deletePredmet(predmetId);
    }


    // ---> Get Predmet - GET <---
    @RequestMapping(value="/predmet/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPredmet(@PathVariable Integer id) { 
        return simpleProxy.getPredmet(id);
    }


    // ---> Get All Predmet - GET <---
    @RequestMapping(value="/predmeti", method = RequestMethod.GET)
    public List<Predmet> getAllPredmet() { 
        return simpleProxy.dajSvePredmete();		
    }




    // -------------> Ucenik <-------------

    // ---> Create Ucenik - POST <---
    @RequestMapping(value="/ucenik", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUcenik(@RequestBody Ucenik ucenik) { 
        Ucenik ucenikData = ucenikService.save(ucenik);
        return ResponseEntity.ok(ucenikData);
    }

    // ---> Update Ucenik - PUT <---
    @RequestMapping(value="/ucenik/{ucenikId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUcenik(@RequestBody Ucenik ucenikNovi, @PathVariable Integer ucenikId) { 
        Ucenik ucenik = null;
        try {
            ucenik = ucenikService.getUcenikById(ucenikId).get();
            ucenik.setIme(ucenikNovi.getIme());
            ucenik.setPrezime(ucenikNovi.getPrezime());
            ucenik = ucenikService.save(ucenik);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("Ne postoji ucenik sa trazenim id-om."));
        }
        return ResponseEntity.ok(ucenik);
    }


    // ---> Delete Ucenik - DELETE <---
    @RequestMapping(value="/ucenik/{ucenikId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteUcenik(@PathVariable Integer ucenikId) { 
        Ucenik ucenik = null;
        try {
            ucenik = ucenikService.getUcenikById(ucenikId).get();
            ucenikService.delete(ucenik);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("Ne postoji ucenik sa trazenim id-om."));
        }
        return ResponseEntity.ok(ucenik);
    }


    // ---> Get Ucenik - GET <---
    @RequestMapping(value="/ucenik/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUcenik(@PathVariable Integer id) { 
        Ucenik ucenik = null;
        try {
            ucenik = ucenikService.getUcenikById(id).get();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("Ne postoji ucenik sa trazenim id-om."));
        }
        return ResponseEntity.ok(ucenik);
    }


    // ---> Get All Ucenik - GET <---
    @RequestMapping(value="/ucenici", method = RequestMethod.GET)
    public List<Ucenik> getAllUcenik() { 
        return ucenikService.dajSveUcenike();		
    }



    // -------------> UcenikPredmeta <-------------

    // ---> Create UcenikPredmeta - POST <---
    @RequestMapping(value="/upisiUcenikaNaPredmet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUcenik(@RequestBody UcenikPredmeta ucenikPredmeta) { 
        UcenikPredmeta ucenikPredmetaData = null;
        try {
            Ucenik ucenik = ucenikService.getUcenikById(ucenikPredmeta.getUcenikId()).get();
            Predmet predmet = predmetService.getPredmetById(ucenikPredmeta.getPredmetId()).get();
            ucenikPredmeta.setUcenik(ucenik);
            ucenikPredmeta.setPredmet(predmet);
            ucenikPredmetaData = ucenikPredmetaService.save(ucenikPredmeta);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("Nevalidan ucenikId ili predmetId."));
        }
        return ResponseEntity.ok(ucenikPredmetaData);
    }

    // ---> Delete Ucenik - DELETE <---
    @RequestMapping(value="/ispisiUcenikaSaPredmeta/{ucenikId}/{predmetId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteUcenik(@PathVariable Integer ucenikId,@PathVariable Integer predmetId) { 
        Ucenik ucenik = null;
        
        try {
            for (UcenikPredmeta ucenikPredmeta : ucenikPredmetaService.getAllUcenikPredmeta()) {
                if (ucenikPredmeta.getUcenik().getId() == ucenikId && ucenikPredmeta.getPredmet().getId() == predmetId){
                    ucenikPredmetaService.delete(ucenikPredmeta);
                }
            }

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("Ne postoji ucenik sa trazenim id-om."));
        }
        return ResponseEntity.ok(ucenik);
    }

    // -------------> Zadaca <-------------

    @RequestMapping(value="/zadaca", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveZadaca(@RequestBody Zadaca zadaca) { 
        Zadaca zadacaData = zadacaService.save(zadaca);
        return ResponseEntity.ok(zadacaData);
    }

    @RequestMapping(value="/dodjeliZadacuPredmetu/{zadacaId}/{predmetId}", method = RequestMethod.GET)
    public ResponseEntity<Object> dodjeliZadacuPredmetu(@PathVariable Integer zadacaId, @PathVariable Integer predmetId) { 
        Zadaca zadacaData = null;
        try {
            Zadaca zadaca = zadacaService.getZadacaById(zadacaId).get();
            Predmet predmet = predmetService.getPredmetById(predmetId).get();
            zadaca.setPredmet(predmet);
            zadacaData = zadacaService.save(zadaca);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("Nevalidan zadacaId ili predmetId."));
        }
        return ResponseEntity.ok(zadacaData);
       
    }


    @RequestMapping(value="/bodoviZadaca", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> bodoviZadaca(@RequestBody BodoviZadaca bodoviZadaca) { 
        BodoviZadaca bodoviZadacaData = null;
        System.out.println(bodoviZadaca.getUcenikId());
        try {
            Ucenik ucenik = ucenikService.getUcenikById(bodoviZadaca.getUcenikId()).get();
            Zadaca zadaca = zadacaService.getZadacaById(bodoviZadaca.getZadacaId()).get();
            bodoviZadacaData = bodoviZadacaService.save(new BodoviZadaca(zadaca,ucenik,bodoviZadaca.getBodovi()));
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("Zadaca ili ucenik sa trazenim id-om ne postoji!"));
        }
        return ResponseEntity.ok(bodoviZadacaData);
    }

    @RequestMapping(value="/bodoviZadace", method = RequestMethod.GET)
    public List<BodoviZadaca> getAllBodoviZadaca() { 
        return bodoviZadacaService.getAllBodoviZadaca();
    }

    @RequestMapping(value="/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<Object>  uploadSingleFile(@RequestParam("file") MultipartFile multipart, @RequestParam("zadacaId") String zadacaId) throws IOException {
        Zadaca zadacaData = null;
        try {
            Zadaca zadaca = zadacaService.getZadacaById(Integer.parseInt(zadacaId)).get();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            Path tempFile = Files.createTempFile(multipart.getOriginalFilename().split("\\.")[0] , multipart.getContentType().split("/")[1]);
            Files.write(tempFile, multipart.getBytes());
            File file = tempFile.toFile();
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new FileSystemResource(file));
        

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            String serverUrl = "http://localhost:3000/upload/";
            RestTemplate restTemplate = new RestTemplate();
            Map<String,String> response = restTemplate.postForObject(serverUrl, requestEntity, Map.class);
            

            zadaca.setFileName(multipart.getOriginalFilename().split("\\.")[0]);
            zadaca.setFileType(multipart.getContentType());
            zadaca.setFileId(response.get("id"));
            zadaca.setWebContentLink(response.get("webContentLink"));
            zadaca.setWebViewLink(response.get("webViewLink"));

            zadacaData = zadacaService.save(zadaca);

        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("Google drive upload greska!"));
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greska("Zadaca sa trazenim id-om ne postoji!"));
        }
        return ResponseEntity.ok(zadacaData);
    }
    
}