package com.example.uploadservice.service;


import com.example.uploadservice.model.TaskFile;
import com.example.uploadservice.repository.TaskFileRepository;
import com.example.uploadservice.service.TaskFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Optional;


@Service
public class TaskFileServiceImpl implements TaskFileService {
    @Autowired
    private TaskFileRepository taskFileRepository;

    @Override
    public Optional<TaskFile> getTaskFileById(Integer id) {
        return taskFileRepository.findById(id);
    }

    @Override
    public TaskFile save(TaskFile taskFile) {
        return taskFileRepository.save(taskFile);
    }

    @Override
    public List<TaskFile> getAllTaskFiles() {
        return taskFileRepository.findAll();
    }

    @Override
    public TaskFile getTaskFileByFileId(String fileId) {
        return taskFileRepository.findByFileId(fileId);
    }
    public void delete(TaskFile taskFile){
        taskFileRepository.delete(taskFile);
    }
}