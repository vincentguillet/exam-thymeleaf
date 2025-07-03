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

/**
 * TaskController handles task-related requests and interactions.
 * It provides endpoints to display, create, edit, and delete tasks.
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    private final UserService userService;
    private final ProjectService projectService;

    /**
     * Displays the list of tasks and a form to create a new task.
     *
     * @param edit  the ID of the task to edit, if any
     * @param model the model to add attributes for the view
     * @return the name of the view to render
     */
    @GetMapping
    public String showTasks(@RequestParam(required = false) Long edit, Model model) {
        model.addAttribute("tasks", taskService.findAllTasks().stream().map(taskMapper::toDTO).toList());
        model.addAttribute("task", new TaskDTO());
        model.addAttribute("editingId", edit != null ? edit : -1L);
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("projects", projectService.findAllProjects());
        return "tasks";
    }

    /**
     * Handles the creation of a new task.
     *
     * @param dto   the task data transfer object containing task details
     * @param model the model to add attributes for the view
     * @return the name of the view to render or redirect if successful
     */
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

    /**
     * Handles the editing of an existing task.
     *
     * @param id     the ID of the task to edit
     * @param title  the new title of the task
     * @param status the new status of the task
     * @return a redirect to the tasks list
     */
    @PostMapping("/edit/{id}")
    public String editTask(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String status) {
        TaskDTO dto = new TaskDTO();
        dto.setId(id);
        dto.setTitle(title);
        dto.setStatus(status);

        taskService.updateTask(dto);
        return "redirect:/tasks";
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id the ID of the task to delete
     * @return a redirect to the tasks list
     */
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/tasks";
    }
}
