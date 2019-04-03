package com.example.nwtizostanakservice.service;

import com.example.nwtizostanakservice.model.Izostanak;
import com.example.nwtizostanakservice.model.Predmet;
import com.example.nwtizostanakservice.model.Ucenik;
import com.example.nwtizostanakservice.repository.IzostanakRepository;
import com.example.nwtizostanakservice.repository.PredmetRepository;
import com.example.nwtizostanakservice.repository.UcenikRepository;
import com.example.nwtizostanakservice.service.IzostanakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Optional;
import java.sql.Date;

@Service
public class IzostanakServiceImpl implements IzostanakService {
    @Autowired
    private IzostanakRepository izostanakRepository;

    @Autowired
    private PredmetRepository predmetRepository;

    @Autowired
    private UcenikRepository ucenikRepository;

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
        Predmet p = predmetRepository.findById(predmetid);
        return izostanakRepository.findByPredmet(p);
    }

    @Override
    public List<Izostanak> findByUcenikid(int ucenikid) {
        Ucenik u = ucenikRepository.findById(ucenikid);
        return izostanakRepository.findByUcenik(u);
    }


    @Override
    public List<Izostanak> dajSveIzostankeUcenikaPredmeta(Integer ucenikid, Integer predmetid) throws Exception {
        if(ucenikid == null) {
            throw new Exception("Ucenik id mora imati neku vrijednost");
        }
        if(predmetid == null) {
            throw new Exception("Predmet id mora imati neku vrijednost");
        }
        Ucenik ucenik;
        try {
            ucenik = ucenikRepository.findById(ucenikid).get(); 
        } catch(Exception e) {
            throw new Exception("Ne postoji ucenik sa tim id-om");
        }

        Predmet predmet;
        try {
            predmet = predmetRepository.findById(predmetid).get();
        } catch(Exception e) {
            throw new Exception("Ne postoji predmet sa tim id-om");
        }
        List<Izostanak> izostanaks = izostanakRepository.findByUcenik(ucenik);
        List<Izostanak> izostanakReturn = new ArrayList<>();
        for (Izostanak izostanak : izostanaks) {
            if(izostanak.getPredmet().getId().equals(predmetid))
            izostanakReturn.add(izostanak);
        }

        return izostanakReturn;
    }

    @Override
    public void delete(Izostanak izostanak){
        izostanakRepository.delete(izostanak);
    }
}
