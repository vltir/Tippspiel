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

    /**
     * Makes a GET request to the OpenLigaDB API and returns the response body as a string.
     *
     * @param endpoint The API endpoint to fetch data from.
     * @return The response body as a string.
     * @throws IOException If an I/O error occurs while making the HTTP request.
     */
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

    /**
     * Parses the list of teams participating in Euro 2024.
     *
     * @return List of 'Verein' objects representing teams.
     */
    public List<Verein> parseLaenderAusEM() {
        List<Verein> teams = new ArrayList<>();
        try {
            String json = fetchFromApi("/getavailableteams/em/2024");

            // Deserialize JSON to a list of maps
            List<Map<String, Object>> teamsData = objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>(){});

            // Iterate over the list of teams and create 'Verein' objects
            for (Map<String, Object> teamData : teamsData) {
                int teamId = (int) teamData.get("teamId");
                String teamName = (String) teamData.get("teamName");
                String shortName = (String) teamData.get("shortName");
                String teamIconUrl = (String) teamData.get("teamIconUrl");
                String teamGroupName = (String) teamData.get("teamGroupName");

                // Create a new 'Verein' object and add it to the list
                Verein verein = new Verein();
                verein.setVereinsId(teamId);
                verein.setVereinsName(teamName);
                verein.setShortname(shortName);
                verein.setBild(teamIconUrl);

                teams.add(verein);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error parsing teams data from API", e);
        }

        return teams;
    }

    /**
     * Parses matches for a specific group in Euro 2024.
     *
     * @param groupOrderID The order ID of the group for which matches are to be parsed.
     * @return List of 'Spiel' objects representing matches.
     */
    public List<Spiel> parseSpieleFuerGruppenspieltag(int groupOrderID) {
        List<Spiel> spiele = new ArrayList<>();
        try {
            String json = fetchFromApi("/getmatchdata/em/2024/" + groupOrderID);

            // Deserialize JSON to a list of maps
            List<Map<String, Object>> matchData = objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>(){});
            for (Map<String, Object> data : matchData) {
                Spiel spiel = new Spiel();
                spiel.setMatchID((Integer) data.get("matchID"));
                spiel.setSpielZeit((String) data.get("matchDateTime"));
                spiel.setLiga((String) data.get("leagueName"));
               String datum = (String) data.get("matchDateTime");
               String datumstripped = datum.substring(0, 9);
               spiel.setDatum(datumstripped);

                // Parse the group
                Map<String, Object> groupData = (Map<String, Object>) data.get("group");
                Group group = new Group();
                group.setGroupName((String) groupData.get("groupName"));
                group.setGroupOrderID((Integer) groupData.get("groupOrderID"));
                group.setGroupID((Integer) groupData.get("groupID"));
                spiel.setGroup(group);

                // Parse team 1
                Map<String, Object> team1Data = (Map<String, Object>) data.get("team1");
                Verein team1 = new Verein();
                team1.setVereinsId((Integer) team1Data.get("teamId"));
                team1.setVereinsName((String) team1Data.get("teamName"));
                team1.setShortname((String) team1Data.get("shortName"));
                team1.setBild((String) team1Data.get("teamIconUrl"));
                spiel.setHeimverein(team1);

                // Parse team 2
                Map<String, Object> team2Data = (Map<String, Object>) data.get("team2");
                Verein team2 = new Verein();
                team2.setVereinsId((Integer) team2Data.get("teamId"));
                team2.setVereinsName((String) team2Data.get("teamName"));
                team2.setShortname((String) team2Data.get("shortName"));
                team2.setBild((String) team2Data.get("teamIconUrl"));
                spiel.setAuswaertsverein(team2);

                spiel.setLastUpdateDateTime((String) data.get("lastUpdateDateTime"));
                spiel.setMatchIsFinished((Boolean) data.get("matchIsFinished"));

                // Parse match results
                List<Map<String, Object>> resultsData = (List<Map<String, Object>>) data.get("matchResults");
                for (Map<String, Object> resultData : resultsData) {
                    if ((Integer) resultData.get("resultTypeID") > 1) {
                        spiel.setHeimVereinTore((Integer) resultData.get("pointsTeam1"));
                        spiel.setAuswaertsVereinTore((Integer) resultData.get("pointsTeam2"));
                    }
                }

                // Add the match to the list
                spiele.add(spiel);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error parsing matches data from API", e);
        }
        return spiele;
    }

    public List<Spiel> parseAlleSpiele() {
        List<Spiel> spiele = new ArrayList<>();
        try {
            String json = fetchFromApi("/getmatchdata/em/2024");

            // Deserialize JSON to a list of maps
            List<Map<String, Object>> matchData = objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>(){});
            for (Map<String, Object> data : matchData) {
                Spiel spiel = new Spiel();
                spiel.setMatchID((Integer) data.get("matchID"));
                spiel.setSpielZeit((String) data.get("matchDateTime"));
                spiel.setLiga((String) data.get("leagueName"));
                String datum = (String) data.get("matchDateTime");
                String datumstripped = datum.substring(0, 9);
                spiel.setDatum(datumstripped);


                // Parse the group
                Map<String, Object> groupData = (Map<String, Object>) data.get("group");
                Group group = new Group();
                group.setGroupName((String) groupData.get("groupName"));
                group.setGroupOrderID((Integer) groupData.get("groupOrderID"));
                group.setGroupID((Integer) groupData.get("groupID"));
                spiel.setGroup(group);

                // Parse team 1
                Map<String, Object> team1Data = (Map<String, Object>) data.get("team1");
                Verein team1 = new Verein();
                team1.setVereinsId((Integer) team1Data.get("teamId"));
                team1.setVereinsName((String) team1Data.get("teamName"));
                team1.setShortname((String) team1Data.get("shortName"));
                team1.setBild((String) team1Data.get("teamIconUrl"));
                spiel.setHeimverein(team1);

                // Parse team 2
                Map<String, Object> team2Data = (Map<String, Object>) data.get("team2");
                Verein team2 = new Verein();
                team2.setVereinsId((Integer) team2Data.get("teamId"));
                team2.setVereinsName((String) team2Data.get("teamName"));
                team2.setShortname((String) team2Data.get("shortName"));
                team2.setBild((String) team2Data.get("teamIconUrl"));
                spiel.setAuswaertsverein(team2);

                spiel.setLastUpdateDateTime((String) data.get("lastUpdateDateTime"));
                spiel.setMatchIsFinished((Boolean) data.get("matchIsFinished"));

                // Parse match results
                List<Map<String, Object>> resultsData = (List<Map<String, Object>>) data.get("matchResults");
                for (Map<String, Object> resultData : resultsData) {
                    if ((Integer) resultData.get("resultTypeID") > 1) {
                        spiel.setHeimVereinTore((Integer) resultData.get("pointsTeam1"));
                        spiel.setAuswaertsVereinTore((Integer) resultData.get("pointsTeam2"));
                    }
                }

                // Add the match to the list
                spiele.add(spiel);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error parsing matches data from API", e);
        }
        return spiele;
    }

    /**
     * Parses all groups participating in Euro 2024.
     *
     * @return List of 'Group' objects representing groups.
     */
    public List<Group> parseAlleGroups() {
        List<Group> groups = new ArrayList<>();
        try {
            String json = fetchFromApi("/getavailablegroups/em/2024");

            // Deserialize JSON to a list of maps
            List<Map<String, Object>> groupData = objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>(){});
            for (Map<String, Object> data : groupData) {
                Group group = new Group();
                group.setGroupName((String) data.get("groupName"));
                group.setGroupOrderID((Integer) data.get("groupOrderID"));
                group.setGroupID((Integer) data.get("groupID"));
                groups.add(group);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error parsing groups data from API", e);
        }
        return groups;
    }

    /**
     * Parses the current active group in Euro 2024.
     *
     * @return 'Group' object representing the current active group.
     */
    public Group parseAktuelleGroup() {
        Group group = new Group();
        try {
            String json = fetchFromApi("/getcurrentgroup/em");

            // Deserialize JSON to a list of maps (assuming it returns a single group)
            Map<String, Object> groupData = objectMapper.readValue(json, new TypeReference<Map<String, Object>>(){});
            group.setGroupName((String) groupData.get("groupName"));
            group.setGroupOrderID((Integer) groupData.get("groupOrderID"));
            group.setGroupID((Integer) groupData.get("groupID"));

        } catch (IOException e) {
            throw new RuntimeException("Error parsing current group data from API", e);
        }
        return group;
    }
}
