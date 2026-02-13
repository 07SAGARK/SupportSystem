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
        long inProgress=repository.countInProgress();
        long resolved=repository.countResolved();
        long open=repository.countOpen();
        model.addAttribute("inProgress", inProgress);
        model.addAttribute("open", open);
        model.addAttribute("resolved", resolved);
        model.addAttribute("total", total);
        return "Home/homePageClient";
    }

    @GetMapping("/admin")
    public String homePageAdmin(){
        return "Home/homePageAdmin";
    }


}
