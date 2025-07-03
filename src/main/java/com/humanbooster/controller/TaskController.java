package com.humanbooster.controller;

import com.humanbooster.dto.TaskDTO;
import com.humanbooster.mapper.TaskMapper;
import com.humanbooster.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;
    private final TaskMapper mapper;

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return service.findAllTasks()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return service.findTaskById(id)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/create")
    public String createTask() {
        return null;
    }
}
