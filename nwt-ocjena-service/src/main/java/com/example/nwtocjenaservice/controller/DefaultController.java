package com.example.nwtocjenaservice.controller;

import com.example.nwtocjenaservice.model.Ocjena;
import com.example.nwtocjenaservice.service.NastavnikService;
import com.example.nwtocjenaservice.service.OcjenaService;
import com.example.nwtocjenaservice.model.Predmet;
import com.example.nwtocjenaservice.service.PredmetService;
import com.example.nwtocjenaservice.service.UcenikPredmetaService;
import com.example.nwtocjenaservice.service.UcenikService;
import com.example.nwtocjenaservice.model.Ucenik;
import com.example.nwtocjenaservice.model.UcenikPredmeta;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.example.nwtocjenaservice.model.Nastavnik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DefaultController {

    @Autowired
    private OcjenaService ocjenaService;

    @Autowired
    private UcenikService ucenikService;

    @Autowired
    private PredmetService predmetService;

    @Autowired
    private UcenikPredmetaService ucenikPredmetaService;

    @Autowired
    private NastavnikService nastavnikService;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstaintViolatoinException(final ConstraintViolationException ex) {

        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            message.append(violation.getMessage().concat(";"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message); 
    }

    @RequestMapping(value="/start", method = RequestMethod.GET)
    public void start() { 

        Ucenik ucenik = new Ucenik(0, "Hamza", "Iseric");
        ucenik= ucenikService.save(ucenik);
		Ucenik ucenik2 = new Ucenik(0, "Enis", "Ahmetovic");
        ucenik2 = ucenikService.save(ucenik2);

        Nastavnik nastavnik = new Nastavnik(0, "Ehvan", "Građanin");
        nastavnik = nastavnikService.save(nastavnik);

        Predmet predmet = new Predmet(0, "IM2", nastavnik);
        predmet = predmetService.save(predmet);

        LocalDate datum = LocalDate.now();

        Ocjena ocjena = new Ocjena(0, 5, datum, ucenik, predmet);
        ocjena = ocjenaService.save(ocjena);
        
        Ocjena ocjena2 = new Ocjena(0, 3, datum, ucenik, predmet);
		ocjena2 = ocjenaService.save(ocjena2);
        Ocjena ocjena3 = new Ocjena(0, 4, datum, ucenik2, predmet);
		ocjena3 = ocjenaService.save(ocjena3);
		
		ucenikPredmetaService.save(new UcenikPredmeta(0, ucenik, predmet));
		ucenikPredmetaService.save(new UcenikPredmeta(0, ucenik2, predmet));
    }
}