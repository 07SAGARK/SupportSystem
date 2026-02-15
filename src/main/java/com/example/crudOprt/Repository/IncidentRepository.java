package com.example.crudOprt.Repository;

import com.example.crudOprt.Entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    long countByCaller(String user);

    long count();
    @Query(value = "Select count(*) from incident where state='IN_PROGRESS' and caller=:user ", nativeQuery = true)
    long countInProgress(String user);

    @Query(value = "Select count(*) from incident where state='RESOLVED' and caller=:user ", nativeQuery = true)
    long countResolved(String user);

    @Query(value = "Select count(*) from incident where state not in ('CANCELLED','RESOLVED') and caller=:user", nativeQuery = true)
    long countOpen(String user);

    @Query(value = "Select count(*) from incident where state='NEW' ", nativeQuery = true)
    long countNew();

    @Query(value = "Select count(*) from incident where state='IN_PROGRESS'",nativeQuery = true)
    long countInProgress();

    @Query(value = "Select count(*) from incident where state='ON_HOLD' ", nativeQuery = true)
    long countOnHold();

    @Query(value = "Select count(*) from incident where state ='RESOLVED' ", nativeQuery = true)
    long countResolved();

    @Query(value = "Select count(*) from incident where priority='1' ", nativeQuery = true)
    long countCritical();

    @Query(value = "Select count(*) from incident where priority='2' ", nativeQuery = true)
    long countHigh();

    @Query(value = "Select count(*) from incident where priority='3' ", nativeQuery = true)
    long countModerate();

    @Query(value = "Select count(*) from incident where priority='4' ", nativeQuery = true)
    long countLow();
}