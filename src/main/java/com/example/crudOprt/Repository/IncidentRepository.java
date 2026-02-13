package com.example.crudOprt.Repository;

import com.example.crudOprt.Entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    long countByCaller(String user);

    @Query(value = "Select count(*) from incident where state='IN_PROGRESS' ", nativeQuery = true)
    long countInProgress();

    @Query(value = "Select count(*) from incident where state='RESOLVED' ", nativeQuery = true)
    long countResolved();

    @Query(value = "Select count(*) from incident where state not in ('CANCELLED','RESOLVED')", nativeQuery = true)
    long countOpen();
}