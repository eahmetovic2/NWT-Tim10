package com.example.nwtocjenaservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nwtocjenaservice.model.Ucenik;


@Repository
public interface UcenikRepository extends JpaRepository<Ucenik, Integer> {

	Ucenik findById(int id);
	
	List<Ucenik> findAll();
	
	Ucenik save(Ucenik ucenik);
	
	Ucenik findByIme(String ime);

	void delete(Ucenik ucenik);
}