package de.dhbwka.tippspiel.model;

public class Tore {

    private int punktZahlHeim;
    private int punktZahlAuswaerts;
    private int spielMinute;

    private String torschuetze;

    private boolean isElfmeter;

    public Tore (int toreHeim, int toreAuswaerts, int spielMinute, String torschuetze, boolean isElfmeter) {
        this.punktZahlHeim = toreHeim;
        this.punktZahlAuswaerts = toreAuswaerts;
        this.spielMinute = spielMinute;
        this.torschuetze = torschuetze;
        this.isElfmeter = isElfmeter;
    }

    public Tore (int toreHeim, int toreAuswaerts) {
        this.punktZahlHeim = toreHeim;
        this.punktZahlAuswaerts = toreAuswaerts;
    }

    public int getPunktZahlHeim() {
        return punktZahlHeim;
    }

    public void setPunktZahlHeim(int punktZahlHeim) {
        this.punktZahlHeim = punktZahlHeim;
    }

    public int getPunktZahlAuswaerts() {
        return punktZahlAuswaerts;
    }

    public void setPunktZahlAuswaerts(int punktZahlAuswaerts) {
        this.punktZahlAuswaerts = punktZahlAuswaerts;
    }

    public int getSpielMinute() {
        return spielMinute;
    }

    public void setSpielMinute(int spielMinute) {
        this.spielMinute = spielMinute;
    }

    public String getTorschuetze() {
        return torschuetze;
    }

    public void setTorschuetze(String torschuetze) {
        this.torschuetze = torschuetze;
    }

    public boolean isElfmeter() {
        return isElfmeter;
    }

    public void setElfmeter(boolean elfmeter) {
        isElfmeter = elfmeter;
    }
}
