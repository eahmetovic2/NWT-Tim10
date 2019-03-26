package com.example.uploadservice.service;


import com.example.uploadservice.model.Predmet;
import com.example.uploadservice.repository.PredmetRepository;
import com.example.uploadservice.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Optional;


@Service
public class PredmetServiceImpl implements PredmetService {
    @Autowired
    private PredmetRepository predmetRepository;

    @Override
    public Optional<Predmet> getPredmetById(Integer id) {
        return predmetRepository.findById(id);
    }

    @Override
    public Predmet save(Predmet predmet) {
        return predmetRepository.save(predmet);
    }

    @Override
    public List<Predmet> getAllPredmet() {
        return predmetRepository.findAll();
    }

    @Override
    public Predmet getPredmetByNaziv(String naziv) {
        return predmetRepository.findByNaziv(naziv);
    }
    public void delete(Predmet predmet){
        predmetRepository.delete(predmet);
    }
}