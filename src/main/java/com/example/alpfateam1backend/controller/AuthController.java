package com.example.alpfateam1backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.alpfateam1backend.model.User.Role; // Import Role enum
import com.example.alpfateam1backend.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login"; // Return login view
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register"; // Return registration form view
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, 
                               @RequestParam String password,
                               @RequestParam String email,
                               @RequestParam String role) { // Accepting role as String
        Role userRole = Role.valueOf(role.toUpperCase()); // Convert String to Role enum
        userService.registerUser(username, password, email, userRole);
        return "redirect:/login"; // Redirect to login after registration
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        // Logic to determine user type and redirect accordingly
        return "dashboard"; // Return dashboard view
    }
}
