package com.example.nwtocjenaservice.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nwtocjenaservice.model.UcenikPredmeta;
import com.example.nwtocjenaservice.model.Predmet;


@Repository
public interface UcenikPredmetaRepository extends JpaRepository<UcenikPredmeta, Integer> {

	UcenikPredmeta findById(int id);
	
	List<UcenikPredmeta> findByPredmet(Predmet predmet);
	
	List<UcenikPredmeta> findAll();
	
	UcenikPredmeta save(UcenikPredmeta ucenikPredmeta);
	
	void delete(UcenikPredmeta ucenikPredmeta);
}