package com.example.auth.service;


import com.example.auth.model.AppUser;
import com.example.auth.repository.AppUserRepository;
import com.example.auth.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Optional;


@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public Optional<AppUser> getAppUserById(Integer id) {
        return appUserRepository.findById(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    public void delete(AppUser appUser){
        appUserRepository.delete(appUser);
    }
}