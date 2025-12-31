package com.example.crudOprt.Entity;

import com.example.crudOprt.Enums.IncidentStatus;
import jakarta.persistence.*;

@Entity
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private String assignmentGroup;

    @Enumerated(EnumType.STRING)
    private IncidentStatus State;

    private int Priority;


}