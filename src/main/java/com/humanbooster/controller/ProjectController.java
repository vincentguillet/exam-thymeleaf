package com.humanbooster.controller;

import com.humanbooster.dto.ProjectDTO;
import com.humanbooster.mapper.ProjectMapper;
import com.humanbooster.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;
    private final ProjectMapper mapper;

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return service.findAllProjects()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        return service.findProjectById(id)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/create")
    public String createProject() {
        return null;
    }
}
