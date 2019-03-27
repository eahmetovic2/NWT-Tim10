package com.example.nwtizostanakservice.service;

import com.example.nwtizostanakservice.model.Predmet;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

public interface PredmetService {
	
	Optional<Predmet> getPredmetById(Integer id);
	
    Predmet save(Predmet predmet);
    
   	List<Predmet> dajSvePredmete();

	void delete(Predmet predmet);
}