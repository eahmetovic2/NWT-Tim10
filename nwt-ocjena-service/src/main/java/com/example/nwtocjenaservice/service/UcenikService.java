package com.example.nwtocjenaservice.service;

import com.example.nwtocjenaservice.model.Ucenik;
import java.util.List;
import java.util.Optional;

public interface UcenikService {
	
	Optional<Ucenik> getUcenikById(Integer id);
	
    Ucenik save(Ucenik ucenik);
    
   	List<Ucenik> dajSveUcenike();

	Ucenik getUcenikByName(String ime);

	void delete(Ucenik ucenik);
}