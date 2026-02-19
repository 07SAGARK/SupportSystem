package com.example.crudOprt.Service;

import com.example.crudOprt.Entity.Incident;
import com.example.crudOprt.Enums.IncidentStatus;
import com.example.crudOprt.Repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IncidentServiceImpl {

    private IncidentRepository repository;

    @Autowired
    public IncidentServiceImpl(IncidentRepository repository){
        this.repository=repository;
    }

    public String createIncident(Incident incident){
        if (incident==null){
            return "No Proper Information is provided";
        }
        repository.save(incident);
        return "Incident created Successfully";
    }


   public List<Incident> getAll(){
        List<Incident> incidentList=repository.findAll();
        return incidentList;
   }
    public void deleteIncident(Long id){
        // If the status is CANCELLED the Incident will be deleted, the filter for this is applied in the Controller
        Optional<Incident> incident=repository.findById(id);
        repository.deleteById(id);
    }

    public List<Map<String, Object>> getTeamCount(){
        List<Object[]> result=repository.countByTeam();
        List<Map<String, Object>> response=new ArrayList<>();
        for (Object[] row:result){
            Map<String, Object> map=new HashMap<>();
            map.put("team", row[0]);
            map.put("count", row[1]);
            response.add(map);
        }
        return response;
    }

    public  Incident finById(long id){
        Optional<Incident> incident=repository.findById(id);
        Incident result=null;
        if (incident.isPresent()){
            result=incident.get();
        }else {
            throw new RuntimeException("No Incident found for the Id "+id);
        }
        return result;


    }
//    public ResponseEntity<?> updateIncident(long id, Incident incident){
//        // logic for updating the incident has been added
//        Optional<Incident>  incdnt=repository.findById(id);
//        if (incdnt==null){
//            return ResponseEntity.badRequest().body("No Such Incident Exist");
//        }
//        Incident inc=incdnt.get();
//        inc.setAssignedTo(incident.getAssignedTo());
//        inc.setState(incident.getState());
//        inc.setPriority(incident.getPriority());
//        inc.setAssignmentGroup(incident.getAssignmentGroup());
//        inc.setDescription(incident.getDescription());
//        repository.save(inc);
//        return ResponseEntity.ok("Incident Updated");
//    }

}
