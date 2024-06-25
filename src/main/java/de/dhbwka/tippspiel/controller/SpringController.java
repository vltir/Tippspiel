package de.dhbwka.tippspiel.controller;

import de.dhbwka.tippspiel.model.Group;
import de.dhbwka.tippspiel.model.OpenLigaDBParser;
import de.dhbwka.tippspiel.model.Spiel;
import de.dhbwka.tippspiel.repositories.RoleRepository;
import de.dhbwka.tippspiel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/em2024")
public class SpringController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private OpenLigaDBParser parser = new OpenLigaDBParser();

    @GetMapping("/results/current")
    public ModelAndView showResultPage(@CookieValue("authToken") String authToken, Model model) {
        model.addAttribute("authToken", authToken);
        Group group = parser.parseAktuelleGroup();
        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(group.getGroupOrderID());
        model.addAttribute("spiele", spiele);
        return new ModelAndView("results.html");
    }
}
