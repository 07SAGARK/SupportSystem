package com.example.crudOprt.Controller;

import com.example.crudOprt.Entity.ProjectUser;
import com.example.crudOprt.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agent")
public class UserController {
    @Autowired
    private UserServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody ProjectUser projectUser){
       return service.addUser(projectUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        return null;
    }
}
