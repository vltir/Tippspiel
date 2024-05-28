package de.dhbwka.tippspiel.controller;
import de.dhbwka.tippspiel.entities.User;
import de.dhbwka.tippspiel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public ModelAndView login(Model model) {
        return new ModelAndView("login.html");
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {
        model.addAttribute("user", new User());
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@RequestParam(value="login_type")String loginType, User user) {
        user.setPasswort(passwordEncoder.encode(user.getPasswort()));
        userRepository.save(user);
        return new ModelAndView("redirect:/login.html");
    }
}
