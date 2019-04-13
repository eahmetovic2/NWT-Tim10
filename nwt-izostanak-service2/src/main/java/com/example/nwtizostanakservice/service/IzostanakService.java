package com.example.nwtizostanakservice.service;

import com.example.nwtizostanakservice.model.Izostanak;
import java.util.List;
import java.util.Collection;
import java.util.Optional;
import java.sql.Date;

public interface IzostanakService {
	
	Optional<Izostanak> getIzostanakById(Integer id);
	
    Izostanak save(Izostanak ucenik);
    
    List<Izostanak> dajSveIzostanke();	   
    
    List<Izostanak> findByDatum(Date datum);
	   
    List<Izostanak> findByPredmetid(int predmetid);

    List<Izostanak> findByUcenikid(int ucenikid);

    List<Izostanak> dajSveIzostankeUcenikaPredmeta(Integer ucenikid, Integer predmetid) throws Exception;
    

	void delete(Izostanak ucenik);
}