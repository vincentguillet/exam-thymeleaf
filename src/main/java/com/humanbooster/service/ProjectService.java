package com.humanbooster.service;

import com.humanbooster.model.Project;
import com.humanbooster.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final List<Project> projects = new ArrayList<>();
    private long nextId = 1;
    private final UserService userService;
    private final TaskService taskService;

    public ProjectService(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    public List<Project> findAllProjects() {
        return projects;
    }

    public Optional<Project> findProjectById(Long id) {
        return projects
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public void addProject(Project project) {
        project.setId(nextId++);
        userService.findUserById(project.getCreator().getId())
                .ifPresent(project::setCreator);
        projects.add(project);
    }

    public void deleteProjectById(Long id) {
        Optional<Project> project = findProjectById(id);
        project.ifPresent(p -> {
            List<Task> tasksToRemove = new ArrayList<>(p.getTasks());
            tasksToRemove.forEach(task -> taskService.deleteTaskById(task.getId()));

            projects.remove(p);
        });
    }
}