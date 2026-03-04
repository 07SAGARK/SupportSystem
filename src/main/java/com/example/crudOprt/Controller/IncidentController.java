package com.example.crudOprt.Controller;

import com.example.crudOprt.Entity.Incident;
import com.example.crudOprt.Enums.IncidentStatus;
import com.example.crudOprt.Repository.IncidentRepository;
import com.example.crudOprt.Service.IncidentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/incident")
public class IncidentController {
    private IncidentServiceImpl service;


    @Autowired
    public IncidentController(IncidentServiceImpl service){
        this.service=service;
    }

    @GetMapping("/create")
    public String createIncident(){
        return "Incident/create";
    }
    @PostMapping("/create")
    public String createIncident(Incident incident){
         service.createIncident(incident);
         return "redirect:/home/client";
    }

    @GetMapping("/view")
    public String viewAll(Model model){
        List<Incident> incidentList=service.getAll();
        model.addAttribute("incident", incidentList);
        return "Incident/viewAll";

    }

//    @GetMapping("/viewByUser")
//    public String viewByUser(){
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        String email=authentication.getName();
//        List<Object[]>
//    }

    @GetMapping("/update")
    public String updateIncident(@RequestParam("id") long id, Model model){
        Incident incident=service.finById(id);
        model.addAttribute("incident", incident);
        return "Incident/update";
    }

    @PostMapping("/update")
    public String updateIncident(@ModelAttribute Incident incident){
        return null;
    }


}
