package com.example.demo.final_project.controller;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/")
public class HomeController {
    
    @GetMapping
    public String index(Principal principal) {//principal è l'oggetto che contiene le informazioni dell'utente autenticato
        // controlla se l'utente è autenticato
        if(principal != null){
            return "redirect:/admin/manga";
        } 
        return "redirect:/login";
    }
    

}
