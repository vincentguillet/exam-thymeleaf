package com.humanbooster.controller;

import com.humanbooster.dto.UserDTO;
import com.humanbooster.mapper.UserMapper;
import com.humanbooster.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * UserController handles user-related requests and interactions.
 * It provides endpoints to display, create, and delete users.
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Displays the list of users and a form to create a new user.
     *
     * @param model the model to add attributes for the view
     * @return the name of the view to render
     */
    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers()
                .stream()
                .map(userMapper::toDTO)
                .toList());
        model.addAttribute("user", new UserDTO());
        return "users";
    }

    /**
     * Handles the creation of a new user.
     *
     * @param userDTO the user data transfer object containing user details
     * @param result  the binding result to capture validation errors
     * @param model   the model to add attributes for the view
     * @return the name of the view to render or redirect if successful
     */
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

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return a redirect to the users list
     */
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
