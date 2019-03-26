package com.example.nwtocjenaservice.service;

import com.example.nwtocjenaservice.model.Nastavnik;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

public interface NastavnikService {
	
	Optional<Nastavnik> getNastavnikById(Integer id);
	
    Nastavnik save(Nastavnik nastavnik);
    
   	List<Nastavnik> dajSveNastavnike();

	Nastavnik getNastavnikByName(String ime);

	void delete(Nastavnik nastavnik);
}