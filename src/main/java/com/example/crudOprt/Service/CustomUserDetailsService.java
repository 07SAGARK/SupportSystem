package com.example.crudOprt.Service;

import com.example.crudOprt.Entity.ProjectUser;
import com.example.crudOprt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ProjectUser projectUser =repository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Invalid Username"));

        return org.springframework.security.core.userdetails.User.withUsername(projectUser.getEmail()).password(projectUser.getPassword()).roles(projectUser.getRole().name()).build();
    }
}
