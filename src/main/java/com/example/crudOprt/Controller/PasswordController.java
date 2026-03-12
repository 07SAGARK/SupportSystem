package com.example.crudOprt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forgot")
public class PasswordController {

    @GetMapping("/email")
    public String getEmail(){
        return "forgotPassword";
    }
}
