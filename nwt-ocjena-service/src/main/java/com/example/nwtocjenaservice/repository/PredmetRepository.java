package com.example.nwtocjenaservice.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nwtocjenaservice.model.Nastavnik;
import com.example.nwtocjenaservice.model.Predmet;


@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Integer> {

	Predmet findById(int id);
	
	List<Predmet> findAll();
	
	Predmet save(Predmet predmet);	
	
	void delete(Predmet predmet);
	
	List<Predmet> findByNastavnik(Nastavnik nastavnik);
}