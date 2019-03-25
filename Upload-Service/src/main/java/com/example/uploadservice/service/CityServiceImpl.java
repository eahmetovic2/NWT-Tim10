package com.example.uploadservice.service;


import com.example.uploadservice.model.City;
import com.example.uploadservice.repository.CityRepository;
import com.example.uploadservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Optional;


@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public Optional<City> getCityById(Integer id) {
        return cityRepository.findById(id);
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityByName(String name) {
        return cityRepository.findByName(name);
    }
    public void delete(City city){
        cityRepository.delete(city);
    }
}