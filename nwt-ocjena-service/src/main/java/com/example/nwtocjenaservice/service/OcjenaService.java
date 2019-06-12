package com.example.nwtocjenaservice.service;

import com.example.nwtocjenaservice.model.Ocjena;
import java.util.List;
import java.util.Optional;

public interface OcjenaService {
	
	Optional<Ocjena> getOcjenaById(Integer id);
	
    Ocjena save(Ocjena ucenik);
    
	List<Ocjena> dajSveOcjene();	   
	   
   	List<Ocjena> dajSveOcjeneUcenika(int ucenikId);

	List<Ocjena> dajSveOcjenePredmeta(int predmetaId);

	void delete(Ocjena ucenik);

	List<Ocjena> dajSveOcjeneUcenikaPredmeta(Integer ucenikId, Integer predmetId) throws Exception;
	
}