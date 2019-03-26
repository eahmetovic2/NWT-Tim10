package com.example.uploadservice.service;

import com.example.uploadservice.model.Zadaca;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

public interface ZadacaService {
	
	Optional<Zadaca> getZadacaById(Integer id);
	
    Zadaca save(Zadaca zadaca);
    
   	List<Zadaca> getAllZadaca();

	Zadaca getZadacaByFileName(String fileName);
	void delete(Zadaca zadaca);
}