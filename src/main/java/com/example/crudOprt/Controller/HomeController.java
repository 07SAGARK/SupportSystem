package com.example.crudOprt.Controller;

import com.example.crudOprt.Repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private IncidentRepository repository;
    @GetMapping("/client")
    public String homeClient(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        long total=repository.countByCaller(email);
        long inProgress=repository.countInProgress(email);
        long resolved=repository.countResolved(email);
        long open=repository.countOpen(email);
        model.addAttribute("inProgress", inProgress);
        model.addAttribute("open", open);
        model.addAttribute("resolved", resolved);
        model.addAttribute("total", total);
        return "Home/homePageClient";
    }

    @GetMapping("/admin")
    public String homePageAdmin(Model model){
        long total= repository.count();
        model.addAttribute("total", total);
        long newCount=repository.countNew();
        long inProgress=repository.countInProgress();
        long pending=repository.countOnHold();
        long resolved=repository.countResolved();
        long critical=repository.countCritical();
        long high=repository.countHigh();
        long moderate=repository.countModerate();
        long low=repository.countLow();
        model.addAttribute("newCount", newCount);
        model.addAttribute("inProgress", inProgress);
        model.addAttribute("resolved", resolved);
        model.addAttribute("pending", pending);
        model.addAttribute("critical", critical);
        model.addAttribute("high", high);
        model.addAttribute("moderate",moderate);
        model.addAttribute("low", low);;
        return "Home/homePageAdmin";
    }


}
