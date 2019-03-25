package com.example.uploadservice.service;

import com.example.uploadservice.model.TaskFile;
import java.util.List;
import java.util.Collection;
import java.util.Optional;

public interface TaskFileService {
	
	Optional<TaskFile> getTaskFileById(Integer id);
	
    TaskFile save(TaskFile taskFile);
    
   	List<TaskFile> getAllTaskFiles();

	TaskFile getTaskFileByFileId(String fileId);
	void delete(TaskFile taskFile);
}