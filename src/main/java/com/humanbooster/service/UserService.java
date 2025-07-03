package com.humanbooster.service;

import com.humanbooster.model.Project;
import com.humanbooster.model.Task;
import com.humanbooster.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();
    private long nextId = 1;

    private final ProjectService projectService;
    private final TaskService taskService;

    public UserService(@Lazy ProjectService projectService, @Lazy TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    public List<User> findAllUsers() {
        return users;
    }

    public Optional<User> findUserById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public void addUser(User user) {
        user.setId(nextId++);
        users.add(user);
    }

    public void deleteUserById(Long id) {
        Optional<User> user = findUserById(id);
        user.ifPresent(u -> {
            List<Project> userProjects = projectService.findAllProjects()
                    .stream()
                    .filter(p -> p.getCreator().getId().equals(id))
                    .toList();
            userProjects.forEach(p -> projectService.deleteProjectById(p.getId()));

            List<Task> userTasks = taskService.findAllTasks()
                    .stream()
                    .filter(t -> t.getAssignee().getId().equals(id))
                    .toList();
            userTasks.forEach(t -> taskService.deleteTaskById(t.getId()));

            users.remove(u);
        });
    }
}
