package de.dhbwka.tippspiel.model;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpenLigaDBParser {

    private static final String BASE_URL = "https://api.openligadb.de";
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public OpenLigaDBParser() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    private String fetchFromApi(String endpoint) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            return response.body().string();
        }
    }

    private List<Verein> parseLaenderAusEM() {
        List<Verein> laender = new ArrayList<>();
        try {
            String json = fetchFromApi("/getavailableteams/em/2024");

            // Deserialisieren des JSON-Strings in eine Liste von Maps
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> teamsData = objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>(){});

            // Iterieren über die Liste der Teams und Vereine erstellen
            for (Map<String, Object> teamData : teamsData) {
                int teamId = (int) teamData.get("teamId");
                String teamName = (String) teamData.get("teamName");
                String shortName = (String) teamData.get("shortName");
                String teamIconUrl = (String) teamData.get("teamIconUrl");
                String teamGroupName = (String) teamData.get("teamGroupName");

                // Erstelle ein neues Verein-Objekt und füge es zur Liste hinzu
                Verein verein = new Verein();
                verein.setVereinsName(teamName);
                verein.setBild(teamIconUrl);

                laender.add(verein);
            }

        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Parsen der Länderdaten aus der API", e);
        }

        return laender;
    }

    private List<Spiel> parseSpieleFuerGruppenspieltag(int groupOrderID) {
        List<Spiel> spiele = new ArrayList<>();
        try {
            String json = fetchFromApi("/getmatchdata/em/2024/" + groupOrderID);

            // Deserialisieren des JSON-Strings in eine Liste von Maps
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> matchData = objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>(){});
            for (Map<String, Object> data : matchData) {
                Spiel spiel = new Spiel();
                spiel.setMatchID((Integer) data.get("matchID"));
                spiel.setSpielZeit((String) data.get("matchDateTime"));
                spiel.setLiga((String) data.get("leagueName"));



                // Parsing der Gruppe
                Map<String, Object> groupData = (Map<String, Object>) data.get("group");
                Group group = new Group();
                group.setGroupName((String) groupData.get("groupName"));
                group.setGroupOrderID((Integer) groupData.get("groupOrderID"));
                group.setGroupID((Integer) groupData.get("groupID"));
                spiel.setGroup(group);

                // Parsing der Teams
                Map<String, Object> team1Data = (Map<String, Object>) data.get("team1");
                Verein team1 = new Verein();
                team1.setVereinsId((Integer) team1Data.get("teamId"));
                team1.setVereinsName((String) team1Data.get("teamName"));
                team1.setShortname((String) team1Data.get("shortName"));
                team1.setBild((String) team1Data.get("teamIconUrl"));
                spiel.setHeimverein(team1);

                Map<String, Object> team2Data = (Map<String, Object>) data.get("team2");
                Verein team2 = new Verein();
                team2.setVereinsId((Integer) team2Data.get("teamId"));
                team2.setVereinsName((String) team2Data.get("teamName"));
                team2.setShortname((String) team2Data.get("shortName"));
                team2.setBild((String) team2Data.get("teamIconUrl"));
                spiel.setAuswaertsverein(team2);

                spiel.setLastUpdateDateTime((String) data.get("lastUpdateDateTime"));
                spiel.setMatchIsFinished((Boolean) data.get("matchIsFinished"));

                // Parsing der Ergebnisse
                List<Map<String, Object>> resultsData = (List<Map<String, Object>>) data.get("matchResults");

                for (Map<String, Object> resultData : resultsData) {

                    if ((Integer) resultData.get("resultTypeID") > 1) {
                        spiel.setHeimVereinTore((Integer) resultData.get("pointsTeam1"));
                        spiel.setAuswaertsVereinTore((Integer) resultData.get("pointsTeam2"));                    }
                }

                // Hinzufügen des Spiels zur Liste
                spiele.add(spiel);
            }



        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Parsen der Länderdaten aus der API", e);
        }
        return spiele;
    }

    private List<Group> parseAlleGroups() {
        List<Group> groups = new ArrayList<>();
        try {
            String json = fetchFromApi("/getavailablegroups/em/2024");

            // Deserialisieren des JSON-Strings in eine Liste von Maps
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> groupData = objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>(){});
            for (Map<String, Object> data : groupData) {
                Group group = new Group();
                group.setGroupName((String) groupData.get("groupName"));
                group.setGroupOrderID((Integer) groupData.get("groupOrderID"));
                group.setGroupID((Integer) groupData.get("groupID"));
                groups.add(group);
            }

        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Parsen der Länderdaten aus der API", e);
        }
        return groups;
    }

    private Group parseAktuelleGroup() {
        Group group = new Group();
        try {
            String json = fetchFromApi("/getcurrentgroup/em");

            // Deserialisieren des JSON-Strings in eine Liste von Maps
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> groupData = objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>(){});
            for (Map<String, Object> data : groupData) {
                group.setGroupName((String) groupData.get("groupName"));
                group.setGroupOrderID((Integer) groupData.get("groupOrderID"));
                group.setGroupID((Integer) groupData.get("groupID"));
            }

        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Parsen der Länderdaten aus der API", e);
        }
        return group;
    }


}

