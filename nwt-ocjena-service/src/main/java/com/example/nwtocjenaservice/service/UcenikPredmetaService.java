package com.example.nwtocjenaservice.service;

import com.example.nwtocjenaservice.model.UcenikPredmeta;
import java.util.List;
import java.util.Optional;

public interface UcenikPredmetaService {
	
	Optional<UcenikPredmeta> getUcenikPredmetaById(Integer id);
	
    UcenikPredmeta save(UcenikPredmeta ucenikPredmeta);
    
   	List<UcenikPredmeta> dajSveUcenikePredmeta(int predmetId);

	void delete(UcenikPredmeta ucenikPredmeta);
}