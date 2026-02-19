package com.example.crudOprt.Entity;

import com.example.crudOprt.Enums.IncidentStatus;
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
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String caller;
    private String assignmentGroup;
    private String assignedTo;
    @Enumerated(EnumType.STRING)
    private IncidentStatus state;
    private Integer priority;
    private String resolutionNote;

}