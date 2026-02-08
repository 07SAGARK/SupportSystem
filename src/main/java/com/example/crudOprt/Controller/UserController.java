package com.example.crudOprt.Controller;

import com.example.crudOprt.Entity.ProjectUser;
import com.example.crudOprt.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agent")
public class UserController {
    @Autowired
    private UserServiceImpl service;

    @GetMapping("/login")
    public String loginPage() {
        return "Login/login";
    }

    @GetMapping("/register")
    public String register() {
        return "Register/register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute ProjectUser projectUser) {
        service.addUser(projectUser);
        return "redirect:/agent/login";
    }

    @GetMapping("/Home/homePageAdmin")
    public String adminHome(){
        return "Home/homePageAdmin";
    }
    @GetMapping("/Home/homePageClient")
    public String clientHome(){
        return "Home/homePageClient";
    }


}
