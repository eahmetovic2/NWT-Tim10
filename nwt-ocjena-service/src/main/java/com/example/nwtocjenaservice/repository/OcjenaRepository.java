package com.example.nwtocjenaservice.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nwtocjenaservice.model.Ocjena;
import com.example.nwtocjenaservice.model.Ucenik;
import com.example.nwtocjenaservice.model.Predmet;


@Repository
public interface OcjenaRepository extends JpaRepository<Ocjena, Integer> {

	Ocjena findById(int id);
	
	List<Ocjena> findAll();
	
	List<Ocjena> findByUcenik(Ucenik ucenik);

	List<Ocjena> findByPredmet(Predmet predmet);
	
	Ocjena save(Ocjena ocjena);

	void delete(Ocjena ocjena);
}