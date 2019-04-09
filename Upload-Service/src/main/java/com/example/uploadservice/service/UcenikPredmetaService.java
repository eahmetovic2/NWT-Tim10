package com.example.uploadservice.service;

import com.example.uploadservice.model.UcenikPredmeta;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

public interface UcenikPredmetaService {
	
	Optional<UcenikPredmeta> getUcenikPredmetaById(Integer id);
	
    UcenikPredmeta save(UcenikPredmeta ucenikPredmeta);
    
   	List<UcenikPredmeta> getAllUcenikPredmeta();

	void delete(UcenikPredmeta ucenikPredmeta);
}