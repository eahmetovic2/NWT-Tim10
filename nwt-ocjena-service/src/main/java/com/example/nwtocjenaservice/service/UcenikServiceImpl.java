package com.example.nwtocjenaservice.service;

import com.example.nwtocjenaservice.model.Ucenik;
import com.example.nwtocjenaservice.repository.UcenikRepository;
import com.example.nwtocjenaservice.service.UcenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UcenikServiceImpl implements UcenikService {
    @Autowired
    private UcenikRepository ucenikRepository;

    @Override
    public Optional<Ucenik> getUcenikById(Integer id) {
        return ucenikRepository.findById(id);
    }

    @Override
    public Ucenik save(Ucenik ucenik) {
        return ucenikRepository.save(ucenik);
    }

    @Override
    public List<Ucenik> dajSveUcenike() {
        return ucenikRepository.findAll();
    }

    @Override
    public Ucenik getUcenikByName(String ime) {
        return ucenikRepository.findByIme(ime);
    }
    public void delete(Ucenik ucenik){
        ucenikRepository.delete(ucenik);
    }
}