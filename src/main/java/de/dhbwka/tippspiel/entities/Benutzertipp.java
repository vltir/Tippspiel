package de.dhbwka.tippspiel.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tipps")
public class Benutzertipp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "heimvereintore", nullable = false)
    private Long heimvereintore;
    @Column(name = "auswaertsvereintore", nullable = false)
    private Long auswaertsvereintore;
    @Column(name = "matchID", nullable = false)
    private Long matchID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getHeimvereintore() {
        return heimvereintore;
    }

    public void setHeimvereintore(Long heimvereintore) {
        this.heimvereintore = heimvereintore;
    }

    public Long getAuswaertsvereintore() {
        return auswaertsvereintore;
    }

    public void setAuswaertsvereintore(Long auswaertsvereintore) {
        this.auswaertsvereintore = auswaertsvereintore;
    }

    public Long getMatchID() {
        return matchID;
    }

    public void setMatchID(Long matchID) {
        this.matchID = matchID;
    }
}
