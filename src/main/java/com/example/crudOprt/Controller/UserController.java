package com.example.crudOprt.Controller;

import com.example.crudOprt.Entity.ProjectUser;
import com.example.crudOprt.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agent")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody ProjectUser projectUser){
       return service.addUser(projectUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("Login successful");
    }
}
