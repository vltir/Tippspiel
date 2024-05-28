package de.dhbwka.tippspiel.controller;

import de.dhbwka.tippspiel.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringController {

    @GetMapping("/signin")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

}
