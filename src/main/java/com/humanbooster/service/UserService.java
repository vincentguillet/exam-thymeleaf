package com.humanbooster.service;

import com.humanbooster.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public List<User> findAllUsers() {
        return List.of();
    }

    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }
}
