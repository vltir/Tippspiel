package de.dhbwka.tippspiel.controller;

import de.dhbwka.tippspiel.entities.Benutzerpunkte;
import de.dhbwka.tippspiel.entities.Benutzertipp;
import de.dhbwka.tippspiel.model.Group;
import de.dhbwka.tippspiel.model.OpenLigaDBParser;
import de.dhbwka.tippspiel.model.Spiel;
import de.dhbwka.tippspiel.repositories.BenutzerpunkteRepository;
import de.dhbwka.tippspiel.repositories.BenutzertippRepository;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private BenutzerpunkteRepository bpRepo;

    @Autowired
    private BenutzertippRepository btRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private OpenLigaDBParser parser = new OpenLigaDBParser();


    @GetMapping("/tipps/current")
    public ModelAndView showTippsPageCurrent(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
            model.addAttribute("authToken", authToken);

            Group group = parser.parseAktuelleGroup();
            List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(group.getGroupOrderID());
            model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

            Map<Integer, Benutzertipp> benutzertipps = new HashMap<>();
            for (Spiel spiel : spiele) {
                if(tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()) != null) {
                    benutzertipps.put(spiel.getMatchID(), tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()));
                }
            }
            model.addAttribute("tippListe", benutzertipps);

            return new ModelAndView("tippseite.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/tipps/1")
    public ModelAndView showTippsPage1(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
            model.addAttribute("authToken", authToken);

            List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(1);
            model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

            Map<Integer, Benutzertipp> benutzertipps = new HashMap<>();
            for (Spiel spiel : spiele) {
                if(tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()) != null) {
                    benutzertipps.put(spiel.getMatchID(), tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()));
                }
            }
            model.addAttribute("tippListe", benutzertipps);

            return new ModelAndView("tippseite.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/tipps/2")
    public ModelAndView showTippsPage2(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
            model.addAttribute("authToken", authToken);

            List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(2);
            model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

            Map<Integer, Benutzertipp> benutzertipps = new HashMap<>();
            for (Spiel spiel : spiele) {
                if(tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()) != null) {
                    benutzertipps.put(spiel.getMatchID(), tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()));
                }
            }
            model.addAttribute("tippListe", benutzertipps);

            return new ModelAndView("tippseite.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/tipps/3")
    public ModelAndView showTippsPage3(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
            model.addAttribute("authToken", authToken);

            List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(3);
            model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

            Map<Integer, Benutzertipp> benutzertipps = new HashMap<>();
            for (Spiel spiel : spiele) {
                if(tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()) != null) {
                    benutzertipps.put(spiel.getMatchID(), tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()));
                }
            }
            model.addAttribute("tippListe", benutzertipps);

            return new ModelAndView("tippseite.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/tipps/4")
    public ModelAndView showTippsPage4(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
            model.addAttribute("authToken", authToken);

            List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(4);
            model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

            Map<Integer, Benutzertipp> benutzertipps = new HashMap<>();
            for (Spiel spiel : spiele) {
                if(tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()) != null) {
                    benutzertipps.put(spiel.getMatchID(), tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()));
                }
            }

            model.addAttribute("tippListe", benutzertipps);

            return new ModelAndView("tippseite.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/tipps/5")
    public ModelAndView showTippsPage5(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
            model.addAttribute("authToken", authToken);

            List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(5);
            model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

            Map<Integer, Benutzertipp> benutzertipps = new HashMap<>();
            for (Spiel spiel : spiele) {
                if(tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()) != null) {
                    benutzertipps.put(spiel.getMatchID(), tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()));
                }
            }
            model.addAttribute("tippListe", benutzertipps);

            return new ModelAndView("tippseite.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/tipps/6")
    public ModelAndView showTippsPage6(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
            model.addAttribute("authToken", authToken);

            List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(6);
            model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

            Map<Integer, Benutzertipp> benutzertipps = new HashMap<>();
            for (Spiel spiel : spiele) {
                if(tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()) != null) {
                    benutzertipps.put(spiel.getMatchID(), tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()));
                }
            }
            model.addAttribute("tippListe", benutzertipps);

            return new ModelAndView("tippseite.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/tipps/7")
    public ModelAndView showTippsPage7(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
            model.addAttribute("authToken", authToken);

            List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(7);
            model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

            Map<Integer, Benutzertipp> benutzertipps = new HashMap<>();
            for (Spiel spiel : spiele) {
                if(tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()) != null) {
                    benutzertipps.put(spiel.getMatchID(), tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()));
                }
            }
            model.addAttribute("tippListe", benutzertipps);

            return new ModelAndView("tippseite.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }

    @PostMapping(value= "/results/tipp", produces = "application/json")
    public ModelAndView sendeTipp(@RequestParam("matchID") int matchID, @RequestParam("HeimVereinTore") Integer heimvereintore, @RequestParam("AuswaertsVereinTore") Integer auswaertsvereintore, @CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
            model.addAttribute("authToken", authToken);

            List<Spiel> spiele = parser.parseAlleSpiele();
            model.addAttribute("spiele", spiele);

            Spiel spiel = tippService.getSpielVonMatchID(matchID, spiele);
            String username = jwtUtils.getUsernameFromJwtToken(authToken);

            if(tippService.getBenutzertippVonUsernameBeiMatchID(username, spiel.getMatchID()) == null) {
                tippService.speichereTippVonBenutzerBeiMatch(username, heimvereintore, auswaertsvereintore, matchID);
            } else {
                tippService.loescheTippVonBenutzerBeiMatch(username, matchID);
                tippService.speichereTippVonBenutzerBeiMatch(username, heimvereintore, auswaertsvereintore, matchID);
            }


            if (spiel.getMatchIsFinished()) {
                int punktzahl = tippService.berechnePunktzahlFuerSpieltipp(heimvereintore, auswaertsvereintore, spiel);
                if (tippService.getBenutzerpunkteVonUsername(username)==null) {
                    tippService.speicherePunktzahlFuerBenutzer(username, punktzahl);
                } else {
                    tippService.loeschePunktzahlFuerBenutzer(username);
                    tippService.speicherePunktzahlFuerBenutzer(username, punktzahl);
                }
            }

            return new ModelAndView("tippseite.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }

    @GetMapping("/results/current")
    public ModelAndView showResultPageCurrent(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
            model.addAttribute("authToken", authToken);

            Group group = parser.parseAktuelleGroup();
            List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(group.getGroupOrderID());
            model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

            return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/1")
    public ModelAndView showResultPage1(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
        model.addAttribute("authToken", authToken);

        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(1);
        model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/2")
    public ModelAndView showResultPage2(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
        model.addAttribute("authToken", authToken);

        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(2);
        model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/3")
    public ModelAndView showResultPage3(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
        model.addAttribute("authToken", authToken);

        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(3);
        model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/4")
    public ModelAndView showResultPage4(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
        model.addAttribute("authToken", authToken);

        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(4);
        model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/5")
    public ModelAndView showResultPage5(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
        model.addAttribute("authToken", authToken);

        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(5);
        model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/6")
    public ModelAndView showResultPage6(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
        model.addAttribute("authToken", authToken);

        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(6);
        model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
    @GetMapping("/results/7")
    public ModelAndView showResultPage7(@CookieValue("authToken") String authToken, Model model) {
        if (jwtUtils.validateJwtToken(authToken)) {
            TippVerarbeitungsService tippService = new TippVerarbeitungsService(this.bpRepo, this.btRepo);
        model.addAttribute("authToken", authToken);

        List<Spiel> spiele = parser.parseSpieleFuerGruppenspieltag(7);
        model.addAttribute("spiele", spiele);

            String username = jwtUtils.getUsernameFromJwtToken(authToken);
            Benutzerpunkte bp = tippService.getBenutzerpunkteVonUsername(username);
            model.addAttribute("benutzerpunkte", bp);

        return new ModelAndView("results.html");
        } else {
            return new ModelAndView("redirect:/api/auth/signin");
        }
    }
}
