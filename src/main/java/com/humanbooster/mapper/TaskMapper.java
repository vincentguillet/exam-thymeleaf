package com.humanbooster.mapper;

import com.humanbooster.dto.TaskDTO;
import com.humanbooster.model.Task;
import com.humanbooster.model.TaskStatus;
import com.humanbooster.model.User;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements Mapper<Task, TaskDTO> {

    @Override
    public Task toEntity(TaskDTO dto) {
        return new Task(
                dto.getId(),
                dto.getTitle(),
                TaskStatus.valueOf(dto.getStatus()),
                new User(dto.getId(), dto.getAssignee())
        );
    }

    @Override
    public TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getStatus().name(),
                task.getAssignee() != null ? task.getAssignee().getUsername() : null
        );
    }
}
