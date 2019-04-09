package com.example.uploadservice.service;


import com.example.uploadservice.model.UcenikPredmeta;
import com.example.uploadservice.repository.UcenikPredmetaRepository;
import com.example.uploadservice.service.UcenikPredmetaService;
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
    public List<UcenikPredmeta> getAllUcenikPredmeta() {
        return ucenikPredmetaRepository.findAll();
    }

    public void delete(UcenikPredmeta ucenikPredmeta){
        ucenikPredmetaRepository.delete(ucenikPredmeta);
    }
}