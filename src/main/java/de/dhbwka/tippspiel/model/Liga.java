package de.dhbwka.tippspiel.model;

import java.util.ArrayList;
import java.util.List;

public class Liga {

    private List<Verein> vereine = new ArrayList<>();
    private List<Spiel> spiele = new ArrayList<>();

    private JsonParser jsonParser = new JsonParser();

    public Liga() {
        // NULL
    }

    public List<Verein> getVereine(String jahr) {
        if(this.vereine.size() != 0) {
            vereine.clear();
        }
        if (this.vereine.size() == 0) {
            hinzufuegenVereine(jahr);
        }
        return this.vereine;
    }

    private void hinzufuegenVereine(String jahr) {
        jsonParser.setUrlBl1Tabelle(jahr);
        this.vereine = jsonParser.getLigaTabelle();
    }

    public void hinzufuegenSpiele(String spieltag, String jahr) {
        jsonParser.setUrlBl1SpieltagUebersicht(spieltag, jahr);
        this.spiele = jsonParser.getAktuellerSpieltagUebersicht();
    }

    public List<Spiel> getSpiele(String spieltag, String jahr) {
        if (this.spiele.size() != 0) {
            spiele.clear();
        }
        hinzufuegenSpiele(spieltag, jahr);

        return this.spiele;
    }

}
