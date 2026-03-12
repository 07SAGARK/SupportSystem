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
    public  void updateIncident(Incident incident){
        Incident incident1=repository.findById(incident.getId()).orElseThrow(()-> new RuntimeException("Incident Not Found"));
         incident1.setDescription(incident.getDescription());
         incident1.setAssignmentGroup(incident.getAssignmentGroup());
         incident1.setState(incident.getState());
         incident1.setAssignedTo(incident.getAssignedTo());
         incident1.setPriority(incident.getPriority());
         incident1.setResolutionNote(incident.getResolutionNote());
         repository.save(incident1);
    }
    public List<Incident> findByUser(String user){
        return repository.findAllByCaller(user);
    }


}
