package com.example.nwtocjenaservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nwtocjenaservice.model.UcenikPredmeta;


@Repository
public interface UcenikPredmetaRepository extends JpaRepository<UcenikPredmeta, Integer> {

	UcenikPredmeta findById(int id);
	
	List<UcenikPredmeta> findByFkPredmetId(int predmetId);
	
	List<UcenikPredmeta> findAll();
	
	UcenikPredmeta save(UcenikPredmeta ucenikPredmeta);
	
	void delete(UcenikPredmeta ucenikPredmeta);
}