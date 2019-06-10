package com.example.uploadservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.uploadservice.model.Zadaca;
import com.example.uploadservice.model.Predmet;

@Repository
public interface ZadacaRepository extends JpaRepository<Zadaca, Integer> {

	Zadaca findById(int id);
	
	List<Zadaca> findAll();

	List<Zadaca> findByPredmetId(Integer predmetId);
	
	Zadaca save(Zadaca zadaca);
	
	Zadaca findByFileName(String fileName);

	void delete(Zadaca zadaca);
}