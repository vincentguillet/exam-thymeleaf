package com.humanbooster.mapper;

import com.humanbooster.dto.ProjectDTO;
import com.humanbooster.model.Project;
import com.humanbooster.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements Mapper<Project, ProjectDTO> {

    @Override
    public Project toEntity(ProjectDTO dto) {
        return new Project(
                dto.getId(),
                dto.getName(),
                new User(dto.getId(), dto.getName()),
                dto.getTasks()

        );
    }

    @Override
    public ProjectDTO toDTO(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getCreator() != null ? project.getCreator().getUsername() : null,
                project.getTasks()
        );
    }
}
