package com.humanbooster.mapper;

/**
 * Mapper interface for converting between Entity and DTO objects.
 * This interface defines methods for mapping data between two types.
 *
 * @param <Entity> the type of the entity object
 * @param <DTO> the type of the Data Transfer Object (DTO)
 */
public interface Mapper<Entity, DTO> {

    /**
     * Converts a DTO to an Entity.
     *
     * @param dto the DTO to convert
     * @return the converted Entity
     */
    Entity toEntity(DTO dto);

    /**
     * Converts an Entity to a DTO.
     *
     * @param entity the Entity to convert
     * @return the converted DTO
     */
    DTO toDTO(Entity entity);
}
