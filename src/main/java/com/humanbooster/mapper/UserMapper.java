package com.humanbooster.mapper;

import com.humanbooster.dto.UserDTO;
import com.humanbooster.model.User;
import org.springframework.stereotype.Component;

/**
 * UserMapper is a component that implements the Mapper interface to convert between User entities and UserDTOs.
 * It provides methods to map UserDTO to User entity and vice versa.
 */
@Component
public class UserMapper implements Mapper<User, UserDTO> {

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param dto the UserDTO to convert
     * @return the converted User entity
     */
    @Override
    public User toEntity(UserDTO dto) {
        return new User(
                dto.getId(),
                dto.getUsername()
        );
    }

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user the User entity to convert
     * @return the converted UserDTO
     */
    @Override
    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername()
        );
    }
}
