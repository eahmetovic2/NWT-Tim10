package com.example.nwtocjenaservice.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nwtocjenaservice.model.Nastavnik;


@Repository
public interface NastavnikRepository extends JpaRepository<Nastavnik, Integer> {

	Nastavnik findById(int id);
	
	List<Nastavnik> findAll();
	
	Nastavnik save(Nastavnik nastavnik);
	
	List<Nastavnik> findByIme(String ime);

	void delete(Nastavnik nastavnik);
}