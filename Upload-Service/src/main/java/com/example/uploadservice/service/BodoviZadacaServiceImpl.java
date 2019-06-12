package com.example.uploadservice.service;


import com.example.uploadservice.model.BodoviZadaca;
import com.example.uploadservice.repository.BodoviZadacaRepository;
import com.example.uploadservice.service.BodoviZadacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Optional;


@Service
public class BodoviZadacaServiceImpl implements BodoviZadacaService {
    @Autowired
    private BodoviZadacaRepository bodoviZadacaRepository;

    @Override
    public Optional<BodoviZadaca> getBodoviZadacaById(Integer id) {
        return bodoviZadacaRepository.findById(id);
    }

    @Override
    public BodoviZadaca save(BodoviZadaca bodoviZadaca) {
        return bodoviZadacaRepository.save(bodoviZadaca);
    }

    @Override
    public List<BodoviZadaca> getAllBodoviZadaca() {
        return bodoviZadacaRepository.findAll();
    }
    @Override
    public List<BodoviZadaca>  getAllBodoviZadacaUcenika(Integer ucenikId) {
        return bodoviZadacaRepository.findByUcenikId(ucenikId);
    }

    public void delete(BodoviZadaca bodoviZadaca){
        bodoviZadacaRepository.delete(bodoviZadaca);
    }
}