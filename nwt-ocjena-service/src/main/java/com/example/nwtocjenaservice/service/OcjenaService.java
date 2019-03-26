package com.example.nwtocjenaservice.service;

import com.example.nwtocjenaservice.model.Ocjena;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

public interface OcjenaService {
	
	Optional<Ocjena> getOcjenaById(Integer id);
	
    Ocjena save(Ocjena ucenik);
    
	List<Ocjena> dajSveOcjene();	   
	   
   	List<Ocjena> dajSveOcjeneUcenika(int ucenikId);

	void delete(Ocjena ucenik);
}