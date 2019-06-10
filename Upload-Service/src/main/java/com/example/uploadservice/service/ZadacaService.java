package com.example.uploadservice.service;

import com.example.uploadservice.model.Zadaca;
import com.example.uploadservice.model.Predmet;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

public interface ZadacaService {
	
	Optional<Zadaca> getZadacaById(Integer id);
	
    Zadaca save(Zadaca zadaca);
    
	//List<Zadaca> getAllZadaca();
	   
	List<Zadaca> getZadacaByPredmet(Predmet predmet);

	Zadaca getZadacaByFileName(String fileName);
	void delete(Zadaca zadaca);
}