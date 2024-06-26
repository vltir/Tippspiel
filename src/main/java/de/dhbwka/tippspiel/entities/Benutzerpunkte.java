package de.dhbwka.tippspiel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "punkte")
public class Benutzerpunkte {

    @Id
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "punktzahl", nullable = false)
    private Long punktzahl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPunktzahl() {
        return punktzahl;
    }

    public void setPunktzahl(Long punktzahl) {
        this.punktzahl = punktzahl;
    }
}
