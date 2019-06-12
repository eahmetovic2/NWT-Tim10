package com.example.nwtocjenaservice.service;

import com.example.nwtocjenaservice.model.Ocjena;
import com.example.nwtocjenaservice.model.Predmet;
import com.example.nwtocjenaservice.model.Ucenik;
import com.example.nwtocjenaservice.repository.OcjenaRepository;
import com.example.nwtocjenaservice.repository.PredmetRepository;
import com.example.nwtocjenaservice.repository.UcenikRepository;
import com.example.nwtocjenaservice.service.OcjenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OcjenaServiceImpl implements OcjenaService {
    @Autowired
    private OcjenaRepository ocjenaRepository;
    @Autowired
    private UcenikRepository ucenikRepository;
    @Autowired
    private PredmetRepository predmetRepository;

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
        Ucenik ucenik = ucenikRepository.findById(ucenikId);
        List<Ocjena> ocjene = ocjenaRepository.findByUcenik(ucenik);
        return ocjene;
    }

    
    @Override
    public List<Ocjena> dajSveOcjenePredmeta(int predmetId) {
        Predmet predmet = predmetRepository.findById(predmetId);
        List<Ocjena> ocjene = ocjenaRepository.findByPredmet(predmet);
        return ocjene;
    }

    @Override
    public List<Ocjena> dajSveOcjeneUcenikaPredmeta(Integer ucenikId, Integer predmetId) throws Exception {
        if(ucenikId == null) {
            throw new Exception("Ucenik id mora imati neku vrijednost");
        }
        if(predmetId == null) {
            throw new Exception("Predmet id mora imati neku vrijednost");
        }
        Ucenik ucenik;
        try {
            ucenik = ucenikRepository.findById(ucenikId).get(); 
        } catch(Exception e) {
            throw new Exception("Ne postoji ucenik sa tim id-om");
        }

        Predmet predmet;
        try {
            predmet = predmetRepository.findById(predmetId).get();
        } catch(Exception e) {
            throw new Exception("Ne postoji predmet sa tim id-om");
        }
        List<Ocjena> ocjene = ocjenaRepository.findByUcenik(ucenik);
        List<Ocjena> ocjeneReturn = new ArrayList<>();
        for (Ocjena ocjena : ocjene) {
            if(ocjena.getPredmet().getId().equals(predmetId))
                ocjeneReturn.add(ocjena);
        }

        return ocjeneReturn;
    }


    @Override
    public void delete(Ocjena ocjena){
        ocjenaRepository.delete(ocjena);
    }
}
