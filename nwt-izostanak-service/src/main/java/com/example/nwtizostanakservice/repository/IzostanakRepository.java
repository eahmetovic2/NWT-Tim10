package com.example.nwtizostanakservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nwtizostanakservice.model.Izostanak;
import java.sql.Date;


@Repository
public interface IzostanakRepository extends JpaRepository<Izostanak, Integer> {

	Izostanak findById(int id);
	
	List<Izostanak> findAll();
	
	Izostanak save(Izostanak izostanak);
    
    List<Izostanak> findByDatum(Date datum);

    List<Izostanak> findByPredmetId(Integer predmetId);

    List<Izostanak> findByUcenikId(Integer ucenik_id);

	void delete(Izostanak izostanak);
}