package com.example.crudOprt.Service;

import com.example.crudOprt.Controller.LoginRequest;
import com.example.crudOprt.Entity.User;
import com.example.crudOprt.Enums.Role;
import com.example.crudOprt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserDetailsService {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository repository,PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.repository=repository;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager=authenticationManager;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=repository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Invalid Username"));
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
