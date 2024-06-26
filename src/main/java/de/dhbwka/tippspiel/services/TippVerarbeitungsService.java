package de.dhbwka.tippspiel.services;

import de.dhbwka.tippspiel.entities.Benutzerpunkte;
import de.dhbwka.tippspiel.entities.Benutzertipp;
import de.dhbwka.tippspiel.model.Spiel;
import de.dhbwka.tippspiel.repositories.BenutzerpunkteRepository;
import de.dhbwka.tippspiel.repositories.BenutzertippRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TippVerarbeitungsService {

    @Autowired
    BenutzerpunkteRepository bpRepo;

    @Autowired
    BenutzertippRepository btRepo;

    public int berechnePunktzahlFuerSpieltipp(int toreHeim, int toreAuswaerts, Spiel spiel) {

        int differenzReal = spiel.getHeimVereinTore() - spiel.getAuswaertsVereinTore();
        int differenzTipp = toreHeim - toreAuswaerts;

        if (differenzReal > 0 && differenzTipp > 0) {
            //
        } else if (differenzReal == 0 && differenzTipp == 0) {
            //
        } else if (differenzReal < 0 && differenzTipp < 0) {
            //
        } else {
            return 0;
        }

        if (differenzReal != differenzTipp) {
            return 1;
        }

        if (toreHeim == spiel.getHeimVereinTore() && toreAuswaerts == spiel.getAuswaertsVereinTore()) {
            return 4;
        } else {
            return 2;
        }
    }

    public Spiel getSpielVonMatchID(int matchID, List<Spiel> spiele) {
        for(Spiel spiel : spiele) {
            if (spiel.getMatchID() == matchID) {
                return spiel;
            }
        }
        return null;
    }

    public void speichereTippVonBenutzerBeiMatch(String username, int toreHeim, int toreAuswaerts, int matchID) {
        Benutzertipp bt = new Benutzertipp();
        bt.setMatchID((long) matchID);
        bt.setUsername(username);
        bt.setHeimvereintore((long) toreHeim);
        bt.setHeimvereintore((long) toreAuswaerts);
        btRepo.save(bt);
    }

    public void speicherePunktzahlFuerBenutzer(String username, int punktzahl) {
        Benutzerpunkte bp = new Benutzerpunkte();
        bp.setPunktzahl((long) punktzahl);
        bp.setUsername(username);
        bpRepo.save(bp);
    }

}
