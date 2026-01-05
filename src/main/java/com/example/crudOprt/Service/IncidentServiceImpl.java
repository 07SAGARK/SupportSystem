package com.example.crudOprt.Service;

import com.example.crudOprt.Entity.Incident;
import com.example.crudOprt.Repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentServiceImpl {

    private IncidentRepository repository;

    @Autowired
    public IncidentServiceImpl(IncidentRepository repository){
        this.repository=repository;
    }

    public ResponseEntity<?> createIncident(Incident incident){
        if (incident==null){
            return ResponseEntity.badRequest().build();
        }
        repository.save(incident);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> updateIncident(long id, Incident incident){
        // logic for updating the incident will be added after some time
        return null;
    }
    public ResponseEntity<?> getIncident(){
        List<Incident> list=repository.findAll();
        if (list.isEmpty()){
            return ResponseEntity.badRequest().body("No Incident created");
        }
        return ResponseEntity.ok().body(list);
    }
    public ResponseEntity<?> deleteIncident(Long id){
        // If the status is CANCELLED the Incident will be deleted, the filter for this is applied in the Controller
        Optional<Incident> incident=repository.findById(id);
        if (incident.isEmpty()){
            return ResponseEntity.badRequest().body("No such Incident exist");
        }
        repository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
