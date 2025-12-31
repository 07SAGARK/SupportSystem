package com.example.crudOprt.Service;

import com.example.crudOprt.Controller.LoginRequest;
import com.example.crudOprt.Entity.User;
import com.example.crudOprt.Enums.Role;
import com.example.crudOprt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl{
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ResponseEntity<?> addUser(User user){
        if (repository.findByEmail(user.getEmail()).isPresent()){
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.AGENT);

        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    private ResponseEntity<?> login(LoginRequest request){

        return null;
    }

}
