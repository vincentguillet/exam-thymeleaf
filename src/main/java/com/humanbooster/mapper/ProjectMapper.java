package com.humanbooster.mapper;

import com.humanbooster.dto.ProjectDTO;
import com.humanbooster.model.Project;
import com.humanbooster.model.User;
import org.springframework.stereotype.Component;

/**
 * ProjectMapper is a component that implements the Mapper interface to convert between Project entities and ProjectDTOs.
 * It provides methods to map ProjectDTO to Project entity and vice versa.
 */
@Component
public class ProjectMapper implements Mapper<Project, ProjectDTO> {

    /**
     * Converts a ProjectDTO to a Project entity.
     *
     * @param dto the ProjectDTO to convert
     * @return the converted Project entity
     */
    @Override
    public Project toEntity(ProjectDTO dto) {
        User creator = new User(dto.getCreatorId(), null);
        return new Project(
                dto.getId(),
                dto.getName(),
                creator,
                dto.getTasks());
    }

    /**
     * Converts a Project entity to a ProjectDTO.
     *
     * @param project the Project entity to convert
     * @return the converted ProjectDTO
     */
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
