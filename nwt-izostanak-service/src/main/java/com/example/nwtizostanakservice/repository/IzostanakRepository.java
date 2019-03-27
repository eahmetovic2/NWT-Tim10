package com.example.nwtizostanakservice.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nwtizostanakservice.model.Izostanak;
import java.sql.Date;


@Repository
public interface IzostanakRepository extends JpaRepository<Izostanak, Integer> {

	Optional<Izostanak> findById(int id);
	
	List<Izostanak> findAll();
	
	Izostanak save(Izostanak izostanak);
    
    List<Izostanak> findByDatum(Date datum);

    List<Izostanak> findByPredmetid(Integer predmetid);

    List<Izostanak> findByUcenikid(Integer ucenikid);

	void delete(Izostanak izostanak);
}