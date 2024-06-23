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


}

