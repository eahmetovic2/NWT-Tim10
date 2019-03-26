package com.example.uploadservice.service;

import com.example.uploadservice.model.Predmet;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

public interface PredmetService {
	
	Optional<Predmet> getPredmetById(Integer id);
	
    Predmet save(Predmet predmet);
    
   	List<Predmet> getAllPredmet();

    Predmet getPredmetByNaziv(String naziv);
	void delete(Predmet predmet);
}