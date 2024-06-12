package de.dhbwka.tippspiel.controller;

import de.dhbwka.tippspiel.entities.ERole;
import de.dhbwka.tippspiel.entities.Role;
import de.dhbwka.tippspiel.entities.User;
import de.dhbwka.tippspiel.services.RoleService;
import de.dhbwka.tippspiel.services.UserDetailsImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import de.dhbwka.tippspiel.repositories.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

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

    @GetMapping("/roles")
    public ModelAndView createRoles(Model model) {
        RoleService rs = new RoleService(roleRepository);
        rs.createDefaultRoles();
        model.addAttribute("logInRequest", new LoginRequest());
        return new ModelAndView("login.html");
    }

    @GetMapping("/signin")
    public ModelAndView showLoginPage(Model model) {
        model.addAttribute("logInRequest", new LoginRequest());
        return new ModelAndView("login.html");
    }

    @GetMapping("/signup")
    public ModelAndView showRegisterPage(Model model) {
        model.addAttribute("signUpRequest", new SignupRequest());
        return new ModelAndView("register.html");
    }

    @PostMapping(value= "/signin", produces = "application/json")
    public ResponseEntity<?> authenticateUser(@RequestParam("username") String username,
                                              @RequestParam("password") String password,
                                              HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(userDetails.getUsername());
        System.out.println(username);
        // Extrahiere die Rollen als Liste von Strings
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // Setzen des Cookies mit dem JWT-Token
        Cookie cookie = new Cookie("authToken", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // false, damit es für http in der lokalen Entwicklungsumgeung gesendet werden kann.
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // Gültigkeit 1 Woche
        response.addCookie(cookie);

        return ResponseEntity.ok(new ModelAndView("login.html"));
        //return ResponseEntity.ok(new JwtResponse(jwt,
          //      userDetails.getId(),
            //    userDetails.getUsername(),
              //  userDetails.getEmail(),
                //roles));
    }

    @PostMapping(value= "/signup", produces = "application/json")
    public ResponseEntity<?> registerUser(@RequestParam("username") String username,
                                          @RequestParam("email") String email,
                                          @RequestParam("password") String password) {
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(email)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));

        // Set the default role (for example: ROLE_USER)
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.getRoles().add(userRole);

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}


