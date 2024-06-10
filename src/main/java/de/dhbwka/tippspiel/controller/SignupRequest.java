package de.dhbwka.tippspiel.controller;

import java.util.Set;

public class SignupRequest {

    private String username;
    private String email;
    private String password;
    private Set<String> role;

    public String getUsername() {
        return username;
    }
    public void setUsername(String benutzername) {
        this.username = benutzername;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String passwort) {
        this.password = passwort;
    }
    public Set<String> getRole() {
        return role;
    }
    public void setRole(Set<String> role) {
        this.role = role;
    }
}
