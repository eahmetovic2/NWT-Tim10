package com.example.uploadservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.uploadservice.model.Predmet;


@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Integer> {

	Predmet findById(int id);
	
	List<Predmet> findAll();
	
	Predmet save(Predmet predmet);
	
	Predmet findByNaziv(String naziv);

	void delete(Predmet predmet);
}