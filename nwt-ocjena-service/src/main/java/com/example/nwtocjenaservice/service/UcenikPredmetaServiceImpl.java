package com.example.nwtocjenaservice.service;

import com.example.nwtocjenaservice.model.UcenikPredmeta;
import com.example.nwtocjenaservice.repository.UcenikPredmetaRepository;
import com.example.nwtocjenaservice.service.UcenikPredmetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

@Service
public class UcenikPredmetaServiceImpl implements UcenikPredmetaService {
    @Autowired
    private UcenikPredmetaRepository ucenikPredmetaRepository;

    @Override
    public Optional<UcenikPredmeta> getUcenikPredmetaById(Integer id) {
        return ucenikPredmetaRepository.findById(id);
    }

    @Override
    public UcenikPredmeta save(UcenikPredmeta ucenikPredmeta) {
        return ucenikPredmetaRepository.save(ucenikPredmeta);
    }

    @Override
    public List<UcenikPredmeta> dajSveUcenikePredmeta(Integer predmetId) {
        return ucenikPredmetaRepository.findByFkPredmetId(predmetId);
    }

    @Override
    public void delete(UcenikPredmeta ucenikPredmeta){
        ucenikPredmetaRepository.delete(ucenikPredmeta);
    }
}