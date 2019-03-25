package com.example.uploadservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.uploadservice.model.City;


@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	City findById(int id);
	
	List<City> findAll();
	
	City save(City city);
	
	City findByName(String name);

	void delete(City city);
}