package com.humanbooster.controller;

import com.humanbooster.dto.UserDTO;
import com.humanbooster.mapper.UserMapper;
import com.humanbooster.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers()
                .stream()
                .map(userMapper::toDTO)
                .toList());
        model.addAttribute("user", new UserDTO());
        return "users";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.findAllUsers()
                    .stream()
                    .map(userMapper::toDTO)
                    .toList());
            return "users";
        }
        userService.addUser(userMapper.toEntity(userDTO));
        return "redirect:/users";
    }
}
