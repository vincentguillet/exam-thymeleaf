package com.humanbooster.controller;

import com.humanbooster.dto.ProjectDTO;
import com.humanbooster.mapper.ProjectMapper;
import com.humanbooster.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;
    private final ProjectMapper mapper;

    @GetMapping
    public String showProjects(Model model) {
        model.addAttribute("projects", service.findAllProjects()
                .stream()
                .map(mapper::toDTO)
                .toList());
        model.addAttribute("project", new ProjectDTO());
        return "projects";
    }

    @PostMapping
    public String createProject(@ModelAttribute("project") ProjectDTO dto, Model model) {
        if (dto.getName() == null || mapper.toEntity(dto).getCreator().getId() == null) {
            model.addAttribute("error", "Champs requis");
            model.addAttribute("projects", service.findAllProjects()
                    .stream()
                    .map(mapper::toDTO)
                    .toList());
            return "projects";
        }
        service.addProject(mapper.toEntity(dto));
        return "redirect:/projects";
    }
}

