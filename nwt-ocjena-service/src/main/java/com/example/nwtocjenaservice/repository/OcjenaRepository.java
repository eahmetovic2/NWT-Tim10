package com.example.nwtocjenaservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nwtocjenaservice.model.Ocjena;


@Repository
public interface OcjenaRepository extends JpaRepository<Ocjena, Integer> {

	Ocjena findById(int id);
	
	List<Ocjena> findAll();
	
	List<Ocjena> findByFkUcenikId(int ucenikId);
	
	Ocjena save(Ocjena ocjena);

	void delete(Ocjena ocjena);
}