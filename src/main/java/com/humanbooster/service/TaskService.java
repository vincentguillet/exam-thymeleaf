package com.humanbooster.service;

import com.humanbooster.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    public List<Task> findAllTasks() {
        return List.of();
    }

    public Optional<Task> findTaskById(Long id) {
        return Optional.empty();
    }
}
