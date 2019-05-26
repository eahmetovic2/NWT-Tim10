package com.example.auth.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth.model.AppUser;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

	AppUser findById(int id);
	
	List<AppUser> findAll();
	
	AppUser save(AppUser appUser);

	void delete(AppUser appUser);
}