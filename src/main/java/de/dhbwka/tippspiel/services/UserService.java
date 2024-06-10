package de.dhbwka.tippspiel.services;

import de.dhbwka.tippspiel.entities.ERole;
import de.dhbwka.tippspiel.entities.Role;
import de.dhbwka.tippspiel.entities.User;
import de.dhbwka.tippspiel.repositories.RoleRepository;
import de.dhbwka.tippspiel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);
    }
}
