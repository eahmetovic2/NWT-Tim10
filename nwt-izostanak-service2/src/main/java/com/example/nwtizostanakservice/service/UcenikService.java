package com.example.nwtizostanakservice.service;

import com.example.nwtizostanakservice.model.Ucenik;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

public interface UcenikService {
	
	Optional<Ucenik> getUcenikById(Integer id);
	
    Ucenik save(Ucenik ucenik);
    
   	List<Ucenik> dajSveUcenike();

	Ucenik getUcenikByName(String ime);

	void delete(Ucenik ucenik);
}