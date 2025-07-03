package com.humanbooster.mapper;

import com.humanbooster.dto.ProjectDTO;
import com.humanbooster.model.Project;
import com.humanbooster.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements Mapper<Project, ProjectDTO> {

    @Override
    public Project toEntity(ProjectDTO dto) {
        User creator = new User(dto.getCreatorId(), null);
        return new Project(
                dto.getId(),
                dto.getName(),
                creator,
                dto.getTasks());
    }

    @Override
    public ProjectDTO toDTO(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getCreator() != null ? project.getCreator().getUsername() : null,
                project.getCreator() != null ? project.getCreator().getId() : null,
                project.getTasks()
        );
    }
}
