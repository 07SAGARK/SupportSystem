package com.example.crudOprt.Entity;

import com.example.crudOprt.Enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String first_name;
    private String last_name;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // this helps to make the password write only which prevents it to get exposed in the client side or in the network
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
