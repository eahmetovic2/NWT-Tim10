package com.example.nwtocjenaservice.service;

import com.example.nwtocjenaservice.model.Nastavnik;
import com.example.nwtocjenaservice.model.Predmet;
import com.example.nwtocjenaservice.repository.NastavnikRepository;
import com.example.nwtocjenaservice.repository.PredmetRepository;
import com.example.nwtocjenaservice.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PredmetServiceImpl implements PredmetService {
    @Autowired
    private PredmetRepository predmetRepository;
    @Autowired
    private NastavnikRepository nastavnikRepository;

    @Override
    public Optional<Predmet> getPredmetById(Integer id) {
        return predmetRepository.findById(id);
    }

    @Override
    public Predmet save(Predmet predmet) {
        return predmetRepository.save(predmet);
    }

    @Override
    public List<Predmet> dajSvePredmete() {
        return predmetRepository.findAll();
    }

    public void delete(Predmet predmet){
        predmetRepository.delete(predmet);
    }

    @Override
    public List<Predmet> dajSvePredmeteNastavnika(int nastavnikId) {
        Nastavnik nastavnik = nastavnikRepository.findById(nastavnikId);
        List<Predmet> predmeti = predmetRepository.findByNastavnik(nastavnik);
        return predmeti;
    }
    
}