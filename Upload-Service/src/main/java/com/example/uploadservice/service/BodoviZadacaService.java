package com.example.uploadservice.service;

import com.example.uploadservice.model.BodoviZadaca;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

public interface BodoviZadacaService {
	
	Optional<BodoviZadaca> getBodoviZadacaById(Integer id);
	
	BodoviZadaca save(BodoviZadaca bodoviZadaca);
	
	List<BodoviZadaca>  getAllBodoviZadacaUcenika(Integer ucenikId);
    
   	List<BodoviZadaca> getAllBodoviZadaca();

	void delete(BodoviZadaca bodoviZadaca);
}