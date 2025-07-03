package com.humanbooster.controller;

import com.humanbooster.dto.TaskDTO;
import com.humanbooster.mapper.TaskMapper;
import com.humanbooster.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;
    private final TaskMapper mapper;

    @GetMapping
    public String showTasks(Model model) {
        model.addAttribute("tasks", service.findAllTasks().stream().map(mapper::toDTO).toList());
        model.addAttribute("task", new TaskDTO());
        return "tasks";
    }

    @PostMapping
    public String createTask(@ModelAttribute("task") TaskDTO dto, Model model) {
        if (dto.getTitle() == null || dto.getProjectId() == null || dto.getAssigneeId() == null) {
            model.addAttribute("error", "Champs requis");
            model.addAttribute("tasks", service.findAllTasks()
                    .stream()
                    .map(mapper::toDTO)
                    .toList());
            return "tasks";
        }

        service.addTask(mapper.toEntity(dto));
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/next-status")
    public String nextStatus(@PathVariable Long id) {
        service.updateStatus(id);
        return "redirect:/tasks";
    }
}
