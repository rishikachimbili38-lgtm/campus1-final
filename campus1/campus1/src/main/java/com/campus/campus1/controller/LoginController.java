package com.campus.campus1.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String login() {
        return "login"; // Renders login.html
    }
    
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
}