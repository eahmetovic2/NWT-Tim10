package com.example.nwtizostanakservice.service;

import com.example.nwtizostanakservice.model.Izostanak;
import com.example.nwtizostanakservice.repository.IzostanakRepository;
import com.example.nwtizostanakservice.service.IzostanakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Optional;
import java.sql.Date;

@Service
public class IzostanakServiceImpl implements IzostanakService {
    @Autowired
    private IzostanakRepository izostanakRepository;

    @Override
    public Optional<Izostanak> getIzostanakById(Integer id) {
        return izostanakRepository.findById(id);
    }

    @Override
    public Izostanak save(Izostanak izostanak) {
        return izostanakRepository.save(izostanak);
    }

    @Override
    public List<Izostanak> dajSveIzostanke() {
        return izostanakRepository.findAll();
    }

    @Override
    public List<Izostanak> findByDatum(Date datum) {
        return izostanakRepository.findByDatum(datum);
    }

    @Override
    public List<Izostanak> findByPredmetid(int predmetid) {
        return izostanakRepository.findByPredmetid(predmetid);
    }

    @Override
    public List<Izostanak> findByUcenikid(int ucenikid) {
        return izostanakRepository.findByUcenikid(ucenikid);
    }






    @Override
    public void delete(Izostanak izostanak){
        izostanakRepository.delete(izostanak);
    }
}
