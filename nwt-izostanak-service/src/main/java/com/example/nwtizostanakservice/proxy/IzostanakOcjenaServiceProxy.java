package com.example.nwtizostanakservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import com.example.nwtizostanakservice.model.Predmet;
import com.example.nwtizostanakservice.model.Ucenik;;


@FeignClient("ocjena-service")

public interface IzostanakOcjenaServiceProxy {

	@GetMapping("/ocjena/sve")
    public ResponseEntity<Object> dajSveOcjene();
    
    @GetMapping("/ucenik/sve")
	public ResponseEntity<Object> dajSveUcenike();

	@GetMapping("/ucenik/{ucnikId}")
	public ResponseEntity<Object>  getUcenik(@PathVariable Integer ucnikId);

	@PostMapping("/ucenik/create")
	public ResponseEntity<Object> saveUcenik(@RequestBody Ucenik ucenik);
}
