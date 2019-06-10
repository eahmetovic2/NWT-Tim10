package com.example.uploadservice.service;


import com.example.uploadservice.model.Zadaca;
import com.example.uploadservice.model.Predmet;
import com.example.uploadservice.repository.ZadacaRepository;
import com.example.uploadservice.service.ZadacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Optional;


@Service
public class ZadacaServiceImpl implements ZadacaService {
    @Autowired
    private ZadacaRepository zadacaRepository;

    @Override
    public Optional<Zadaca> getZadacaById(Integer id) {
        return zadacaRepository.findById(id);
    }

    @Override
    public Zadaca save(Zadaca zadaca) {
        return zadacaRepository.save(zadaca);
    }

    //@Override
    //public List<Zadaca> getAllZadaca() {
    //    return zadacaRepository.findAll();
    //}

    @Override
    public List<Zadaca> getZadacaByPredmetId(Integer predmetId) {
        return zadacaRepository.findByPredmetId(predmetId);//IZMJENA
    }

    @Override
    public Zadaca getZadacaByFileName(String fileName) {
        return zadacaRepository.findByFileName(fileName);
    }
    public void delete(Zadaca zadaca){
        zadacaRepository.delete(zadaca);
    }
}