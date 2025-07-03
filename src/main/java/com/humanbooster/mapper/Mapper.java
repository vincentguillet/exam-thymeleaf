package com.humanbooster.mapper;

public interface Mapper<Entity, DTO> {
    Entity toEntity(DTO dto);
    DTO toDTO(Entity entity);
}
