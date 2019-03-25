package com.example.uploadservice.service;

import com.example.uploadservice.model.City;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

public interface CityService {
	
	Optional<City> getCityById(Integer id);
	
    City save(City city);
    
   	List<City> getAllCities();

	City getCityByName(String name);
	void delete(City city);
}