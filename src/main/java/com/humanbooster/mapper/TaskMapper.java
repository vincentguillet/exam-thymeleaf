package com.humanbooster.mapper;

import com.humanbooster.dto.TaskDTO;
import com.humanbooster.model.Project;
import com.humanbooster.model.Task;
import com.humanbooster.model.TaskStatus;
import com.humanbooster.model.User;
import com.humanbooster.service.ProjectService;
import com.humanbooster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskMapper implements Mapper<Task, TaskDTO> {

    private final UserService userService;
    private final ProjectService projectService;

    @Override
    public Task toEntity(TaskDTO dto) {
        User assignee = userService.findUserById(dto.getAssigneeId()).orElse(null);
        Project project = projectService.findProjectById(dto.getProjectId()).orElse(null);
        TaskStatus status = dto.getStatus() != null ? TaskStatus.valueOf(dto.getStatus()) : TaskStatus.TODO;
        return new Task(
                dto.getId(),
                dto.getTitle(),
                status,
                assignee,
                project
        );
    }

    @Override
    public TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getStatus().name(),
                task.getAssignee() != null ? task.getAssignee().getUsername() : null,
                task.getAssignee() != null ? task.getAssignee().getId() : null,
                task.getProject() != null ? task.getProject().getId() : null,
                task.getProject() != null ? task.getProject().getName() : null
        );
    }
}
