package com.humanbooster.service;

import com.humanbooster.dto.TaskDTO;
import com.humanbooster.model.Task;
import com.humanbooster.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    public List<Task> findAllTasks() {
        return tasks;
    }

    public Optional<Task> findTaskById(Long id) {
        return tasks
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public void addTask(Task task) {
        task.setId(nextId++);
        tasks.add(task);
        if (task.getProject() != null) {
            task.getProject().getTasks().add(task);
        }
    }

    public void deleteTaskById(Long id) {
        tasks.removeIf(u -> u.getId().equals(id));
    }

    public void updateStatus(Long id) {
        findTaskById(id).ifPresent(task -> {
            TaskStatus next = switch (task.getStatus()) {
                case TODO -> TaskStatus.IN_PROGRESS;
                case IN_PROGRESS, DONE -> TaskStatus.DONE;
            };
            task.setStatus(next);
        });
    }

    public void updateTask(TaskDTO dto) {
        Task task = findTaskById(dto.getId()).orElseThrow();
        task.setTitle(dto.getTitle());
        task.setStatus(TaskStatus.valueOf(dto.getStatus()));
        System.out.printf("Updated task %d: title=%s, status=%s%n", task.getId(), task.getTitle(), task.getStatus());
    }
}
