package de.dhbwka.tippspiel.controller;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    JwtResponse(String token, Long id, String username, String email, List<String> roles) {
        this.token = token;
        this.type = type;
        this.id = id;

    }


    // Constructor, getters and setters
}

