package com.humanbooster.mapper;

import com.humanbooster.dto.UserDTO;
import com.humanbooster.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {

    @Override
    public User toEntity(UserDTO dto) {
        return new User(
                dto.getId(),
                dto.getUsername()
        );
    }

    @Override
    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername()
        );
    }
}
