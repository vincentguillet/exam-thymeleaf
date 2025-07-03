package com.humanbooster.controller;

import com.humanbooster.dto.TaskDTO;
import com.humanbooster.mapper.TaskMapper;
import com.humanbooster.service.ProjectService;
import com.humanbooster.service.TaskService;
import com.humanbooster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    private final UserService userService;
    private final ProjectService projectService;

    @GetMapping
    public String showTasks(@RequestParam(required = false) Long edit, Model model) {
        model.addAttribute("tasks", taskService.findAllTasks().stream().map(taskMapper::toDTO).toList());
        model.addAttribute("task", new TaskDTO());
        model.addAttribute("editingId", edit != null ? edit : -1L);
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("projects", projectService.findAllProjects());
        return "tasks";
    }

    @PostMapping
    public String createTask(@ModelAttribute("task") TaskDTO dto, Model model) {
        if (dto.getTitle() == null || dto.getProjectId() == null || dto.getAssigneeId() == null) {
            model.addAttribute("error", "Champs requis");
            model.addAttribute("tasks", taskService.findAllTasks()
                    .stream()
                    .map(taskMapper::toDTO)
                    .toList());
            return "tasks";
        }

        taskService.addTask(taskMapper.toEntity(dto));
        return "redirect:/tasks";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String status) {
        TaskDTO dto = new TaskDTO();
        dto.setId(id);
        dto.setTitle(title);
        dto.setStatus(status);

        System.out.println("DTO reçu : " + dto);

        taskService.updateTask(dto);
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/tasks";
    }
}
