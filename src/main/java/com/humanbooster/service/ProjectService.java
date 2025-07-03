package com.humanbooster.service;

import com.humanbooster.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    public List<Project> findAllProjects() {
        return List.of();
    }

    public Optional<Project> findProjectById(Long id) {
        return Optional.empty();
    }
}