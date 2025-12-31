package com.example.crudOprt.Entity;

import com.example.crudOprt.Enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
