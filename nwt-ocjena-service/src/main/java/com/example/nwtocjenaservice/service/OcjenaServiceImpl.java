package com.example.nwtocjenaservice.service;

import com.example.nwtocjenaservice.model.Ocjena;
import com.example.nwtocjenaservice.repository.OcjenaRepository;
import com.example.nwtocjenaservice.service.OcjenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

@Service
public class OcjenaServiceImpl implements OcjenaService {
    @Autowired
    private OcjenaRepository ocjenaRepository;

    @Override
    public Optional<Ocjena> getOcjenaById(Integer id) {
        return ocjenaRepository.findById(id);
    }

    @Override
    public Ocjena save(Ocjena ocjena) {
        return ocjenaRepository.save(ocjena);
    }

    @Override
    public List<Ocjena> dajSveOcjene() {
        return ocjenaRepository.findAll();
    }

    @Override
    public List<Ocjena> dajSveOcjeneUcenika(int ucenikId) {
        return ocjenaRepository.findByFkUcenikId(ucenikId);
    }

    @Override
    public void delete(Ocjena ocjena){
        ocjenaRepository.delete(ocjena);
    }
}
