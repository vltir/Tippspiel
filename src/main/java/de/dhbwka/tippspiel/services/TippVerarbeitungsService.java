package de.dhbwka.tippspiel.services;

import de.dhbwka.tippspiel.entities.Benutzerpunkte;
import de.dhbwka.tippspiel.entities.Benutzertipp;
import de.dhbwka.tippspiel.model.Spiel;
import de.dhbwka.tippspiel.repositories.BenutzerpunkteRepository;
import de.dhbwka.tippspiel.repositories.BenutzertippRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TippVerarbeitungsService {

    private BenutzerpunkteRepository bpRepo;
    private BenutzertippRepository btRepo;

    public TippVerarbeitungsService(BenutzerpunkteRepository bpRepo, BenutzertippRepository btRepo) {
        this.bpRepo = bpRepo;
        this.btRepo = btRepo;
    }

    public int berechnePunktzahlFuerSpieltipp(long toreHeim, long toreAuswaerts, Spiel spiel) {
        int punkte = 0;
        if(spiel.getMatchIsFinished()) {
            int differenzReal = spiel.getHeimVereinTore() - spiel.getAuswaertsVereinTore();
            long differenzTipp = toreHeim - toreAuswaerts;
            if (differenzReal > 0 && differenzTipp > 0) {
                punkte++;
            } else if (differenzReal == 0 && differenzTipp == 0) {
                punkte++;
            } else if (differenzReal < 0 && differenzTipp < 0) {
                punkte++;
            } else {
                punkte = 0;
            }

            if (toreHeim == spiel.getHeimVereinTore() && toreAuswaerts == spiel.getAuswaertsVereinTore()) {
                punkte += 3;
            } else {
                punkte++;
            }
        }

        return punkte;
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
        bt.setAuswaertsvereintore((long) toreAuswaerts);
        btRepo.save(bt);
    }
    public void loescheTippVonBenutzerBeiMatch(String username, int matchID) {
        btRepo.deleteByUsernameAndMatchID(username, (long) matchID);
    }

    public void speicherePunktzahlFuerBenutzer(String username, int punktzahl) {
        Benutzerpunkte bp = new Benutzerpunkte();
        bp.setPunktzahl((long) punktzahl);
        bp.setUsername(username);
        bpRepo.save(bp);
    }

    public void loeschePunktzahlFuerBenutzer(String username) {
        bpRepo.deleteByUsername(username);
    }

    public Benutzertipp getBenutzertippVonUsernameBeiMatchID(String username, int matchID) {
        try {
            Benutzertipp bt = btRepo.findByUsernameAndMatchID(username, (long) matchID);
            if (bt != null) {
                return bt;
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    public List<Benutzertipp> getAlleBenutzertippsVonUsername(String username) {
        List<Benutzertipp> tipps = btRepo.findByUsername(username);
        return tipps;
    }

    public Benutzerpunkte getBenutzerpunkteVonUsername(String username) {
        try {
            Benutzerpunkte bp = bpRepo.findByUsername(username);
            if (bp != null) {
                return bp;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}