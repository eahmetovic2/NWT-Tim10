package com.example.nwtocjenaservice.service;

import com.example.nwtocjenaservice.model.Nastavnik;
import com.example.nwtocjenaservice.repository.NastavnikRepository;
import com.example.nwtocjenaservice.service.NastavnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NastavnikServiceImpl implements NastavnikService {
    @Autowired
    private NastavnikRepository nastavnikRepository;

    @Override
    public Optional<Nastavnik> getNastavnikById(Integer id) {
        return nastavnikRepository.findById(id);
    }

    @Override
    public Nastavnik save(Nastavnik nastavnik) {
        return nastavnikRepository.save(nastavnik);
    }

    @Override
    public List<Nastavnik> dajSveNastavnike() {
        return nastavnikRepository.findAll();
    }

    @Override
    public List<Nastavnik> getNastavnikByName(String ime) {
        return nastavnikRepository.findByIme(ime);
    }
    public void delete(Nastavnik nastavnik){
        nastavnikRepository.delete(nastavnik);
    }
}