package com.example.crudOprt.Service;

import com.example.crudOprt.Controller.LoginRequest;
import com.example.crudOprt.Entity.User;
import com.example.crudOprt.Enums.Role;
import com.example.crudOprt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl{

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository,PasswordEncoder passwordEncoder){
        this.repository=repository;
        this.passwordEncoder=passwordEncoder;
    }
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
        User user=repository.findByEmail(request.getUsername()).orElseThrow(()-> new UsernameNotFoundException("Invalid Username"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Wrong Password");
        }
        return ResponseEntity.ok(Map.of(
                "message","Login Successful",
                "email",user.getEmail()
        ));
    }

}
