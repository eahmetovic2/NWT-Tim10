package com.example.auth.service;

import com.example.auth.model.AppUser;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

public interface AppUserService {
	
	Optional<AppUser> getAppUserById(Integer id);
	
    AppUser save(AppUser appUser);
    
   	List<AppUser> getAllAppUsers();

	void delete(AppUser appUser);
}