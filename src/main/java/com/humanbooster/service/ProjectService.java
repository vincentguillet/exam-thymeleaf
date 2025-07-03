package com.humanbooster.service;

import com.humanbooster.model.Project;
import com.humanbooster.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing projects.
 * This class provides methods to find, add, delete projects,
 * and handle associated tasks.
 */
@Service
public class ProjectService {

    private final List<Project> projects = new ArrayList<>();
    private long nextId = 1;
    private final UserService userService;
    private final TaskService taskService;

    /**
     * Constructor for ProjectService.
     * Initializes the service with UserService and TaskService instances.
     *
     * @param userService the UserService instance
     * @param taskService the TaskService instance
     */
    public ProjectService(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    /**
     * Retrieves all projects.
     *
     * @return a list of all projects
     */
    public List<Project> findAllProjects() {
        return projects;
    }

    /**
     * Finds a project by its ID.
     *
     * @param id the ID of the project to find
     * @return an Optional containing the project if found, or empty if not found
     */
    public Optional<Project> findProjectById(Long id) {
        return projects
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    /**
     * Adds a new project to the list of projects in memory.
     *
     * @param project the project to add
     */
    public void addProject(Project project) {
        project.setId(nextId++);
        userService.findUserById(project.getCreator().getId())
                .ifPresent(project::setCreator);
        projects.add(project);
    }

    /**
     * Deletes a project by its ID.
     * If the project is found, it is removed from the list of projects,
     * and all associated tasks are deleted.
     *
     * @param id the ID of the project to delete
     */
    public void deleteProjectById(Long id) {
        Optional<Project> project = findProjectById(id);
        project.ifPresent(p -> {
            List<Task> tasksToRemove = new ArrayList<>(p.getTasks());
            tasksToRemove.forEach(task -> taskService.deleteTaskById(task.getId()));

            projects.remove(p);
        });
    }
}