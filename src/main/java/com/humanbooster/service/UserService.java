package com.humanbooster.service;

import com.humanbooster.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();
    private long nextId = 1;

    public List<User> findAllUsers() {
        return users;
    }

    public Optional<User> findUserById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public void addUser(User user) {
        user.setId(nextId++);
        users.add(user);
    }

    public void deleteUserById(Long id) {
        users.removeIf(u -> u.getId().equals(id));
    }
}
