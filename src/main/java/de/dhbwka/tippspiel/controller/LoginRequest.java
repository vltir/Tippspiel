package de.dhbwka.tippspiel.controller;

public class LoginRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String benutzername) {
        this.username = benutzername;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String passwort) {
        this.password = passwort;
    }



}
