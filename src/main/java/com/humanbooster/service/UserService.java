package com.humanbooster.service;

import com.humanbooster.model.Project;
import com.humanbooster.model.Task;
import com.humanbooster.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users.
 * This class provides methods to find, add, and delete users,
 * as well as handling the deletion of associated projects and tasks.
 */
@Service
public class UserService {

    private final List<User> users = new ArrayList<>();
    private long nextId = 1;

    private final ProjectService projectService;
    private final TaskService taskService;

    /**
     * Constructor for UserService.
     * Uses @Lazy annotation to avoid circular dependency issues with ProjectService and TaskService.
     *
     * @param projectService the ProjectService instance
     * @param taskService    the TaskService instance
     */
    public UserService(@Lazy ProjectService projectService, @Lazy TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    public List<User> findAllUsers() {
        return users;
    }

    /**
     * Finds a user by its ID.
     *
     * @param id the ID of the user to find
     * @return an Optional containing the user if found, or empty if not found
     */
    public Optional<User> findUserById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    /**
     * Add a new user to the list of users in memory.
     */
    public void addUser(User user) {
        user.setId(nextId++);
        users.add(user);
    }

    /**
     * Deletes a user by its ID.
     * Also deletes all projects created by the user and all tasks assigned to the user.
     *
     * @param id the ID of the user to delete
     */
    public void deleteUserById(Long id) {
        Optional<User> user = findUserById(id);
        user.ifPresent(u -> {

            // Delete all projects created by the user
            List<Project> userProjects = projectService.findAllProjects()
                    .stream()
                    .filter(p -> p.getCreator().getId().equals(id))
                    .toList();
            userProjects.forEach(p -> projectService.deleteProjectById(p.getId()));

            // Delete all tasks assigned to the user
            List<Task> userTasks = taskService.findAllTasks()
                    .stream()
                    .filter(t -> t.getAssignee().getId().equals(id))
                    .toList();
            userTasks.forEach(t -> taskService.deleteTaskById(t.getId()));

            users.remove(u);
        });
    }
}
