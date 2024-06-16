package de.dhbwka.tippspiel.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonParser {

    private String bl1TabelleJahr = "2024";
    private String bl1SpieltagUebersichtJahr = "2024";
    private String bl1SpieltagAuswahl = "1";

    // private final String urlBl1Spiele =
// "https://www.openligadb.de/api/getmatchdata/bl1";
    private String urlBl1Tabelle;


    private String urlBl1AktuellerSpieltag = "https://api.openligadb.de/getcurrentgroup/bl1";
    private String urlBl1SpieltagUebersicht;

    private String jsonResponseTabelle = "";
    private String jsonResponseSpiele = "";

    private int spieltag = 0;


    public List<Verein> getLigaTabelle() {

        List<Verein> vereine = new ArrayList<Verein>();

        String jsonResponse = getLigaResponseFromAPI(urlBl1Tabelle);
        this.jsonResponseTabelle = jsonResponse;
        vereine = getTabelleJsonResponse(jsonResponse);

        return vereine;
    }

    public List<Spiel> getAktuellerSpieltagUebersicht() {

        List<Spiel> spiele = new ArrayList<>();

        String jsonResponse = getLigaResponseFromAPI(urlBl1SpieltagUebersicht);
        this.jsonResponseSpiele = jsonResponse;
        spiele = getSpieleJsonResponse(jsonResponse);

        return spiele;
    }

    public int getLigaSpieltag() {
        String jsonRespone = getLigaResponseFromAPI(urlBl1AktuellerSpieltag);

        int aktuellerSpieltag = getIntFromJSONObject(new JSONObject(jsonRespone), "groupOrderID");
        setSpieltag(aktuellerSpieltag);

        return spieltag;
    }

    private URL getJsonResponse(String url) {

        System.setProperty("https.proxyHost", "pro-campus.noc.fiducia.de");
        System.setProperty("https.proxyPort", "8080");

        URL response = null;
        HttpURLConnection conn = null;
        int responseCode = 0;

        try {
            response = new URL(url);
            conn = (HttpURLConnection) response.openConnection();
            conn.connect();
            responseCode = conn.getResponseCode();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }

        return response;
    }


    private String getLigaResponseFromAPI(String url) {
        URL urlResponse = getJsonResponse(url);
        String response = responseLesen(urlResponse);
        return response;
    }

    private String responseLesen(URL url) {

        String response = "";

        // Get content of the response
        Scanner sc = null;
        try {
            sc = new Scanner(url.openStream(), "UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // read response and store it as string
        while (sc.hasNext()) {
            response += sc.nextLine();
        }
        sc.close();

        return response;
    }


    public List<Verein> getTabelleJsonResponse(String jsonResponse) {

        List<Verein> tabelle = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonResponse);
        for (int i = 0; i < jsonArray.length(); i++) {
            tabelle.add(konvertierungJsonToVerein(jsonArray.getJSONObject(i)));
        }
        return tabelle;
    }

    public List<Spiel> getSpieleJsonResponse(String jsonResponse) {

        List<Spiel> spiele = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonResponse);

        for (int i = 0; i < jsonArray.length(); i++) {
            spiele.add(konvertierungJsonToSpiel(jsonArray.getJSONObject(i)));
        }
        return spiele;
    }


    private String getStringFromJSONObject(JSONObject jsonObject, String key) {

        String value;


        if (jsonObject == null) {
            value = "";
            return value;
        }

        try {
            value = jsonObject.getString(key);
            value = value.trim();
        }
        catch (Exception e) {
            value = "";
            System.out.println("* Fehler: Der Key " + key + " ist nicht vohanden");
        }

        return value;
    }

    private int getIntFromJSONObject(JSONObject jsonObject, String key) {

        int value;


        if (jsonObject == null) {
            value = 0;
            return value;
        }

        try {
            value = jsonObject.getInt(key);
        }
        catch (Exception e) {
            value = 0;
            System.out.println("* Fehler: Der Key " + key + " ist nicht vohanden");
        }

        return value;
    }

    private boolean getBooleanFromJSONObject(JSONObject jsonObject, String key) {

        boolean is;


        if (jsonObject == null) {
            is = false;
            return is;
        }

        try {
            is = jsonObject.getBoolean(key);
        }
        catch (Exception e) {
            is = false;
            System.out.println("* Fehler: Der Key " + key + " ist nicht vohanden");
        }

        return is;
    }

    private Verein konvertierungJsonToVerein(JSONObject jsonObject) {

        Verein verein = new Verein();

        String vereinsName = getStringFromJSONObject(jsonObject, "teamName");
        int punkte = getIntFromJSONObject(jsonObject, "points");
        int tore = getIntFromJSONObject(jsonObject, "goals");
        int spiele = getIntFromJSONObject(jsonObject, "matches");
        int siege = getIntFromJSONObject(jsonObject, "won");
        int niederlagen = getIntFromJSONObject(jsonObject, "lost");
        int unentschieden = getIntFromJSONObject(jsonObject, "draw");

        verein.setVereinsName(vereinsName);
        verein.setPunkte(punkte);
        verein.setTore(tore);
        verein.setSpiele(spiele);
        verein.setSiege(siege);
        verein.setNiederlagen(niederlagen);
        verein.setUnentschieden(unentschieden);


        return verein;
    }

    private Spiel konvertierungJsonToSpiel(JSONObject jsonObject) {

        Spiel spiel = new Spiel();

        String heimVereinName = "";
        String auswaertsVereinName = "";
        int torErgebnisHeim = 0;
        int torErgebnisAuswaerts = 0;

        JSONObject jsonObjectHeimTeam = jsonObject.getJSONObject("team1");
        if (jsonObjectHeimTeam != null) {
            heimVereinName = getStringFromJSONObject(jsonObjectHeimTeam, "teamName");
        }

        JSONObject jsonObjectAuswaertsTeam = jsonObject.getJSONObject("team2");
        if (jsonObjectAuswaertsTeam != null) {
            auswaertsVereinName = getStringFromJSONObject(jsonObjectAuswaertsTeam, "teamName");
        }

        Verein heimVerein = new Verein(heimVereinName);
        Verein auswaertsVerein = new Verein(auswaertsVereinName);

        spiel.setHeimverein(heimVerein);
        spiel.setAuswaertsverein(auswaertsVerein);

        int arrayPostionSpielErgebnis = 1;

        switch (bl1SpieltagUebersichtJahr) {
            case "2022" :
            case "2021" :
            case "2020" :
                arrayPostionSpielErgebnis = 0;
                break;
            default :
                break;
        }

        JSONArray jsonArraySpielErgebnis = jsonObject.getJSONArray("matchResults");
        if (jsonArraySpielErgebnis != null && jsonArraySpielErgebnis.length() == 2) {
            JSONObject jsonObjectErgebnisEndzeit = jsonArraySpielErgebnis.getJSONObject(arrayPostionSpielErgebnis);
            torErgebnisHeim = getIntFromJSONObject(jsonObjectErgebnisEndzeit, "pointsTeam1");
            torErgebnisAuswaerts = getIntFromJSONObject(jsonObjectErgebnisEndzeit, "pointsTeam2");
        }

        String matchDateTime = getStringFromJSONObject(jsonObject, "matchDateTimeUTC");


        JSONArray jsonArrayTore = jsonObject.getJSONArray("goals");
        int toreHeim = 0;
        int toreAuswaerts = 0;
        int spielMinute = 0;
        String torschuetze = "";
        boolean isElfmeter = false;;
        if (jsonArrayTore != null) {
            for (int i = 0; i < ((JSONArray) jsonArrayTore).length(); i++) {
                JSONObject jsonObjectTor = jsonArrayTore.getJSONObject(i);
                toreHeim = getIntFromJSONObject(jsonObjectTor, "scoreTeam1");
                toreAuswaerts = getIntFromJSONObject(jsonObjectTor, "scoreTeam2");
                spielMinute = getIntFromJSONObject(jsonObjectTor, "matchMinute");
                torschuetze = getStringFromJSONObject(jsonObjectTor, "goalGetterName");
                isElfmeter = getBooleanFromJSONObject(jsonObjectTor, "isPenalty");

                spiel.addSpielTore(toreHeim, toreAuswaerts, spielMinute, torschuetze, isElfmeter);
            }
        }


        spiel.setHeimVereinTore(torErgebnisHeim);
        spiel.setAuswaertsVereinTore(torErgebnisAuswaerts);
        spiel.setDatum(matchDateTime);

        return spiel;


    }

    public void setSpieltag(int spieltag) {
        this.spieltag = spieltag;
    }

    public void setUrlBl1SpieltagUebersicht(String bl1SpieltagAuswahl, String bl1SpieltagUebersichtJahr) {
        this.bl1SpieltagAuswahl = bl1SpieltagAuswahl;
        this.bl1SpieltagUebersichtJahr = bl1SpieltagUebersichtJahr;
        urlBl1SpieltagUebersicht = "https://api.openligadb.de/getmatchdata/bl1/" + this.bl1SpieltagUebersichtJahr + "/"
                + this.bl1SpieltagAuswahl;
    }

    public void setUrlBl1Tabelle(String urlBl1Tabelle) {
        this.bl1TabelleJahr = urlBl1Tabelle;
        this.urlBl1Tabelle = "https://api.openligadb.de/getbltable/bl1/" + bl1TabelleJahr;

    }

}
