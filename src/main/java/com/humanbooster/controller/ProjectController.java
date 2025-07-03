package com.humanbooster.controller;

import com.humanbooster.dto.ProjectDTO;
import com.humanbooster.mapper.ProjectMapper;
import com.humanbooster.service.ProjectService;
import com.humanbooster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    private final UserService userService;

    @GetMapping
    public String showProjects(Model model) {
        model.addAttribute("projects", projectService.findAllProjects()
                .stream()
                .map(projectMapper::toDTO)
                .toList());
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("users", userService.findAllUsers());
        return "projects";
    }

    @PostMapping
    public String createProject(@ModelAttribute("project") ProjectDTO dto, Model model) {
        if (dto.getName() == null || projectMapper.toEntity(dto).getCreator().getId() == null) {
            model.addAttribute("error", "Champs requis");
            model.addAttribute("projects", projectService.findAllProjects()
                    .stream()
                    .map(projectMapper::toDTO)
                    .toList());
            return "projects";
        }
        projectService.addProject(projectMapper.toEntity(dto));
        return "redirect:/projects";
    }

    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        return "redirect:/projects";
    }
}

