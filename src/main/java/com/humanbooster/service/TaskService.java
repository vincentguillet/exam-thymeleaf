package com.humanbooster.service;

import com.humanbooster.dto.TaskDTO;
import com.humanbooster.model.Task;
import com.humanbooster.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing tasks.
 * This class provides methods to find, add, delete, and update tasks.
 */
@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    /**
     * Retrieves all tasks.
     *
     * @return a list of all tasks
     */
    public List<Task> findAllTasks() {
        return tasks;
    }

    /**
     * Finds a task by its ID.
     *
     * @param id the ID of the task to find
     * @return an Optional containing the task if found, or empty if not found
     */
    public Optional<Task> findTaskById(Long id) {
        return tasks
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    /**
     * Adds a new task to the list of tasks in memory.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        task.setId(nextId++);
        tasks.add(task);
        if (task.getProject() != null) {
            task.getProject().getTasks().add(task);
        }
    }

    /**
     * Deletes a task by its ID.
     * If the task is found, it is removed from the list of tasks and also from its associated project.
     *
     * @param id the ID of the task to delete
     */
    public void deleteTaskById(Long id) {
        Optional<Task> task = findTaskById(id);
        task.ifPresent(t -> {
            tasks.remove(t);
            if (t.getProject() != null) {
                t.getProject().getTasks().remove(t);
            }
        });
    }

    /**
     * Updates an existing task.
     * The task is found by its ID, and its title and status are updated.
     *
     * @param dto the TaskDTO containing the updated task information
     */
    public void updateTask(TaskDTO dto) {
        Task task = findTaskById(dto.getId()).orElseThrow();
        task.setTitle(dto.getTitle());
        task.setStatus(TaskStatus.valueOf(dto.getStatus()));
    }
}
