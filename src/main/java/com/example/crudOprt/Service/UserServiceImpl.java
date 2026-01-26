package com.example.crudOprt.Service;

import com.example.crudOprt.Entity.ProjectUser;
import com.example.crudOprt.Enums.Role;
import com.example.crudOprt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private UserRepository repository;
   private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository=repository;
   this.passwordEncoder=passwordEncoder;

    }
    public ResponseEntity<?> addUser(ProjectUser projectUser){
        if (repository.findByEmail(projectUser.getEmail()).isPresent()){
            return ResponseEntity.badRequest().build();
        }
        projectUser.setPassword(passwordEncoder.encode(projectUser.getPassword()));
        projectUser.setRole(Role.AGENT);

        repository.save(projectUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
