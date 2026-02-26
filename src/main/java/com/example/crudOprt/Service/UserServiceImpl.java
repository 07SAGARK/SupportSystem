package com.example.crudOprt.Service;

import com.example.crudOprt.Entity.ProjectUser;
import com.example.crudOprt.Enums.Role;
import com.example.crudOprt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        projectUser.setRole(projectUser.getRole());

        repository.save(projectUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> getEmail(String email){
        Optional<ProjectUser> user=repository.findByEmail(email);
        if (user==null){
            return ResponseEntity.badRequest().body("No Email found!");
        }
        return ResponseEntity.ok().build();

    }

    public ResponseEntity<?> updatePassword(String email, String password){
        Optional<ProjectUser> user=repository.findByEmail(email);
        if (user==null){
            return ResponseEntity.badRequest().body("Wrong Email ");
        }
        ProjectUser user1=user.get();
        System.out.println(user1.toString());
        user1.setPassword(passwordEncoder.encode(password));
        repository.save(user1);
        return ResponseEntity.ok().body("Password Updated Successfully");
    }


}
