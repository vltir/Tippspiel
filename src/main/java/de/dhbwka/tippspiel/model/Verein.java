package de.dhbwka.tippspiel.model;



public class Verein {

    private String vereinsName;
    private int tore = 0;
    private int spiele = 0;
    private int punkte = 0;
    private int siege = 0;
    private int niederlagen = 0;
    private int unentschieden = 0;

    public Verein(String vereinsName) {
        this.vereinsName = vereinsName;
    }

    public Verein() {
        //NULL
    }

    public String getVereinsName() {
        if(this.vereinsName != null) {
            return this.vereinsName;
        }
    }

    public void setVereinsName(String vereinsName) {
        this.vereinsName = vereinsName;
    }

    public int getTore() {
        return tore;
    }

    public void setTore(int tore) {
        this.tore = tore;
    }

    public int getSpiele() {
        return spiele;
    }

    public void setSpiele(int spiele) {
        this.spiele = spiele;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public int getSiege() {
        return siege;
    }

    public void setSiege(int siege) {
        this.siege = siege;
    }

    public int getNiederlagen() {
        return niederlagen;
    }

    public void setNiederlagen(int niederlagen) {
        this.niederlagen = niederlagen;
    }

    public int getUnentschieden() {
        return unentschieden;
    }

    public void setUnentschieden(int unentschieden) {
        this.unentschieden = unentschieden;
    }

}
