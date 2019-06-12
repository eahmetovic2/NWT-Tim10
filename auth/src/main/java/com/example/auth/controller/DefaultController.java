package com.example.auth.controller;

import com.example.auth.model.AppUser;
import com.example.auth.service.AppUserService;


import java.time.LocalDate;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class DefaultController {

    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes="application/json")
    public AppUser create(@RequestBody AppUser model) { 
        
        return appUserService.save(model);
    }

    
}