package com.example.uploadservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.uploadservice.model.TaskFile;


@Repository
public interface TaskFileRepository extends JpaRepository<TaskFile, Integer> {

	TaskFile findById(int id);
	
	List<TaskFile> findAll();
	
	TaskFile save(TaskFile taskFile);
	
	TaskFile findByFileId(String fileId);

	void delete(TaskFile taskFile);
}