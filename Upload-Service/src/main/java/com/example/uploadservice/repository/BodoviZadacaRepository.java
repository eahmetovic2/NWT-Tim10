package com.example.uploadservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.uploadservice.model.BodoviZadaca;


@Repository
public interface BodoviZadacaRepository extends JpaRepository<BodoviZadaca, Integer> {

	BodoviZadaca findById(int id);
	
	List<BodoviZadaca> findAll();
	
	BodoviZadaca save(BodoviZadaca bodoviZadaca);

	void delete(BodoviZadaca bodoviZadaca);
}