package com.humanbooster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController handles requests to the root URL and returns the index view.
 */
@Controller
public class HomeController {

    /**
     * Handles GET requests to the root URL ("/") and returns the index view.
     *
     * @return the name of the index view
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
