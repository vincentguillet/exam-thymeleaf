package com.humanbooster.controller;

import com.humanbooster.dto.UserDTO;
import com.humanbooster.mapper.UserMapper;
import com.humanbooster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return service.findAllUsers()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return service.findUserById(id)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/create")
    public String createUser() {
        return null;
    }
}
