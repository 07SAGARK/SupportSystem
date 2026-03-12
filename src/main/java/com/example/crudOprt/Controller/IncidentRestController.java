package com.example.crudOprt.Controller;

import com.example.crudOprt.Service.IncidentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/incident")
public class IncidentRestController {
    @Autowired
    private IncidentServiceImpl service;

    @GetMapping("/teamCount")
    public List<Map<String, Object>> getTeamCount(){
        return service.getTeamCount();
    }
}
