package de.dhbwka.tippspiel.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "benutzerdaten", schema = "tippspiel-daten")
public class User {

    @jakarta.persistence.Id
    @Column(name= "benutzerid", nullable = false)
    private Long benutzerid;

    @Column(name= "benutzername", nullable = false)
    private String benutzername;

    @Column(name= "passwort", nullable = false)
    private String passwort;

    // Getters and setters

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public Long getBenutzerid() {
        return benutzerid;
    }

    public void setBenutzerid(Long benutzerid) {
        this.benutzerid = benutzerid;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }
}
