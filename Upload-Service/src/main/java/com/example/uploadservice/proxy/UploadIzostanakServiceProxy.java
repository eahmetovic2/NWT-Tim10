package com.example.uploadservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import com.example.uploadservice.model.Predmet;
import com.example.uploadservice.model.Zadaca;

@FeignClient(name="ocjena-service")

public interface UploadIzostanakServiceProxy {

	@GetMapping("/predmet/sve")
	public List<Predmet> dajSvePredmete();

	@GetMapping("/predmet/{id}")
	public Predmet getPredmetP(@PathVariable Integer id);

	@GetMapping("/predmet/{id}")
	public ResponseEntity<Object> getPredmet(@PathVariable Integer id);

	@GetMapping("/zadaca/predmet/{predmetId}")
	public ResponseEntity<Zadaca>  getZadacePredmeta(@PathVariable Integer predmetId);

	@PostMapping("/predmet")
	public ResponseEntity<Object> savePredmet(@RequestBody Predmet predmet);

	@PutMapping("/predmet/{predmetId}")
	public ResponseEntity<Object> updatePredmet(@RequestBody Predmet predmet, @PathVariable Integer predmetId);

	@DeleteMapping("/predmet/{predmetId}")
	public ResponseEntity<Object> deletePredmet(@PathVariable Integer predmetId);
}
