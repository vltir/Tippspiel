package de.dhbwka.tippspiel.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Spiel {

    private Verein heimverein;
    private Verein auswaertsverein;

    private int heimVereinTore;
    private int auswaertsVereinTore;

    private List<Tore> spielTore = new ArrayList<>();

    private String liga;
    private String spielZeit;
    private String datum;

    public Verein getHeimverein() {
        return heimverein;
    }

    public void setHeimverein(Verein heimverein) {
        this.heimverein = heimverein;
    }

    public Verein getAuswaertsverein() {
        return auswaertsverein;
    }

    public void setAuswaertsverein(Verein auswaertsverein) {
        this.auswaertsverein = auswaertsverein;
    }

    public int getHeimVereinTore() {
        return heimVereinTore;
    }

    public void setHeimVereinTore(int heimVereinTore) {
        this.heimVereinTore = heimVereinTore;
    }

    public int getAuswaertsVereinTore() {
        return auswaertsVereinTore;
    }

    public void setAuswaertsVereinTore(int auswaertsVereinTore) {
        this.auswaertsVereinTore = auswaertsVereinTore;
    }

    public List<Tore> getSpielTore() {
        Collections.sort(spielTore, Comparator.comparingInt(Tore -> Tore.getSpielMinute()));
        return spielTore;
    }

    public void addSpielTore(int torHeim, int torAuswaerts, int spielMinute, String torschuetze, boolean isStraftor) {
        spielTore.add(new Tore(torHeim, torAuswaerts, spielMinute, torschuetze, isStraftor));
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getSpielZeit() {
        return spielZeit;
    }

    public void setSpielZeit(String spielZeit) {
        this.spielZeit = spielZeit;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setSpielTore(List<Tore> spielTore) {
        this.spielTore = spielTore;
    }
}
