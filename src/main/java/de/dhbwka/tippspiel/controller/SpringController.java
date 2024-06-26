package de.dhbwka.tippspiel.controller;

import de.dhbwka.tippspiel.model.Group;
import de.dhbwka.tippspiel.model.OpenLigaDBParser;
import de.dhbwka.tippspiel.model.Spiel;
import de.dhbwka.tippspiel.repositories.RoleRepository;
import de.dhbwka.tippspiel.repositories.UserRepository;
import de.dhbwka.tippspiel.services.TippVerarbeitungsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    private TippVerarbeitungsService tippService = new TippVerarbeitungsService();

    @GetMapping("/tipps")
    public ModelAndView showTippsPageCurrent(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            model.addAttribute("authToken", authToken);
            Group group = parser.parseAktuelleGroup();
            List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(group.getGroupOrderID());
            model.addAttribute("spiele", spiele);
            return new ModelAndView("tippseite.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }

    @PostMapping(value= "/results/tipp", produces = "application/json")
    public ModelAndView authenticateUser(@RequestParam("matchID") int matchID, @RequestParam("HeimVereinTore") Integer heimvereintore, @RequestParam("AuswaertsVereinTore") Integer auswaertsvereintore, @CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            model.addAttribute("authToken", authToken);
            Group group = parser.parseAktuelleGroup();
            List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(group.getGroupOrderID());
            model.addAttribute("spiele", spiele);

            Spiel spiel = tippService.getSpielVonMatchID(matchID, spiele);
            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            tippService.speichereTippVonBenutzerBeiMatch(username, heimvereintore, auswaertsvereintore, matchID);

            if (spiel.getMatchIsFinished()) {
                int punktzahl = tippService.berechnePunktzahlFuerSpieltipp(heimvereintore, auswaertsvereintore, spiel);
                tippService.speicherePunktzahlFuerBenutzer(username, punktzahl);
            }

            return new ModelAndView("tippseite.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }

    @GetMapping("/results/current")
    public ModelAndView showResultPageCurrent(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            model.addAttribute("authToken", authToken);
            Group group = parser.parseAktuelleGroup();
            List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(group.getGroupOrderID());
            model.addAttribute("spiele", spiele);
            return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/1")
    public ModelAndView showResultPage1(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
        model.addAttribute("authToken", authToken);
        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(1);
        model.addAttribute("spiele", spiele);
        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/2")
    public ModelAndView showResultPage2(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
        model.addAttribute("authToken", authToken);
        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(2);
        model.addAttribute("spiele", spiele);
        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/3")
    public ModelAndView showResultPage3(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
        model.addAttribute("authToken", authToken);
        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(3);
        model.addAttribute("spiele", spiele);
        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/4")
    public ModelAndView showResultPage4(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
        model.addAttribute("authToken", authToken);
        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(4);
        model.addAttribute("spiele", spiele);
        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/5")
    public ModelAndView showResultPage5(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
        model.addAttribute("authToken", authToken);
        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(5);
        model.addAttribute("spiele", spiele);
        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/6")
    public ModelAndView showResultPage6(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
        model.addAttribute("authToken", authToken);
        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(6);
        model.addAttribute("spiele", spiele);
        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/7")
    public ModelAndView showResultPage7(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
        model.addAttribute("authToken", authToken);
        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(7);
        model.addAttribute("spiele", spiele);
        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
}
