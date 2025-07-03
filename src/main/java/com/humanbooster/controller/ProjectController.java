package com.humanbooster.controller;

import com.humanbooster.dto.ProjectDTO;
import com.humanbooster.mapper.ProjectMapper;
import com.humanbooster.service.ProjectService;
import com.humanbooster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * ProjectController handles project-related requests and interactions.
 * It provides endpoints to display, create, and delete projects.
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    private final UserService userService;

    /**
     * Displays the list of projects and a form to create a new project.
     *
     * @param model the model to add attributes for the view
     * @return the name of the view to render
     */
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

    /**
     * Handles the creation of a new project.
     *
     * @param dto   the project data transfer object containing project details
     * @param model the model to add attributes for the view
     * @return the name of the view to render or redirect if successful
     */
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

    /**
     * Handles the deletion of a project by its ID.
     *
     * @param id the ID of the project to delete
     * @return the name of the view to redirect to after deletion
     */
    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        return "redirect:/projects";
    }
}

