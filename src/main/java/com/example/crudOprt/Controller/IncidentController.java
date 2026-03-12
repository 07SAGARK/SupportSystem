package com.example.crudOprt.Controller;

import com.example.crudOprt.Entity.Incident;
import com.example.crudOprt.Enums.IncidentStatus;
import com.example.crudOprt.Repository.IncidentRepository;
import com.example.crudOprt.Service.IncidentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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
    private IncidentRepository repository;


    @Autowired
    public IncidentController(IncidentServiceImpl service, IncidentRepository repository){
        this.service=service;
        this.repository=repository;
    }

    @GetMapping("/create")
    public String createIncident(Model model){
        model.addAttribute("incident", new Incident());
        return "Incident/create";
    }
    @PostMapping("/create")
    public String createIncident(@ModelAttribute Incident incident){
         service.createIncident(incident);
         return "redirect:/home/client";
    }

    @GetMapping("/view")
    public String viewAll(Model model){
        List<Incident> incidentList=service.getAll();
        model.addAttribute("incident", incidentList);
        long total=repository.count();
        model.addAttribute("total",total);
        return "Incident/viewAll";
    }

    @GetMapping("/viewByUser")
    public String viewByUser(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        List<Incident> incidents=service.findByUser(email);
        model.addAttribute("incident", incidents);
        long total=repository.countByUser(email);
        model.addAttribute("total", total);
        return "Incident/viewAllByUser";
    }

    @GetMapping("/update")
    public String updateIncident(@RequestParam("id") long id, Model model){
        Incident incident=service.finById(id);
        model.addAttribute("incident", incident);
        return "Incident/update";
    }

    @PostMapping("/update")
    public String updateIncident(@ModelAttribute Incident incident){
        service.updateIncident(incident);
        return "redirect:/home/admin";
    }
    @GetMapping("/updateByUser")
    public String updateIncidentByUser(@RequestParam("id") long id, Model model){
        Incident incident=service.finById(id);
        System.out.println("Inside update By User");

        model.addAttribute("incident", incident);
        return "Incident/updateByUser";
    }

    @PostMapping("/updateByUser")
    public String updateIncidentByUser(@ModelAttribute Incident incident){
        service.updateIncident(incident);
        return "redirect:/home/client";
    }


}
