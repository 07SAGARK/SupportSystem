package com.example.crudOprt.Controller;

import com.example.crudOprt.Entity.Incident;
import com.example.crudOprt.Enums.IncidentStatus;
import com.example.crudOprt.Service.IncidentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incident")
public class IncidentController {
    private IncidentServiceImpl service;

    @Autowired
    public IncidentController(IncidentServiceImpl service){
        this.service=service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createIncident(@RequestBody Incident incident){
        return service.createIncident(incident);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateIncident(@PathVariable long id,@RequestBody Incident incident){
        if (incident.getState().equals(IncidentStatus.CANCELLED)){
            return service.deleteIncident(id);
        }
        return service.updateIncident(id,incident);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getIncident(){
        return service.getIncident();
    }



}
