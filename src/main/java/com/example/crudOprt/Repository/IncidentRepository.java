package com.example.crudOprt.Repository;

import com.example.crudOprt.Entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}