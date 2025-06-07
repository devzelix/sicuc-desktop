package com.culturacarabobo.sicuc.desktop.clients;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import com.culturacarabobo.sicuc.desktop.config.EnvConfig;

public class CultorClient {

    // Base URL for the cultors endpoint
    private static String BASE_URL = EnvConfig.getApiUrl() + "/cultors";

    // Sends a GET request with filters to retrieve cultors as a JSON string
    public static String getCultorsJson(String username, String password, int municipalityId, int parishId,
            String gender, int artCategoryId, int artDisciplineId, String disability, String illness, String query) {

        String url = BASE_URL + "?";

        // Append filters to the URL if present
        if (municipalityId > 0)
            url += "municipalityId=" + municipalityId + "&";
        if (parishId > 0)
            url += "parishId=" + parishId + "&";
        if (!gender.isBlank())
            url += "gender=" + gender + "&";
        if (artCategoryId > 0)
            url += "artCategoryId=" + artCategoryId + "&";
        if (artDisciplineId > 0)
            url += "artDisciplineId=" + artDisciplineId + "&";
        if (!disability.isBlank())
            url += "hasDisability=" + disability + "&";
        if (!illness.isBlank())
            url += "hasIllness=" + illness + "&";
        if (!query.isBlank())
            url += "query=" + query + "&";

        // Remove trailing '&' and encode spaces
        url = url.substring(0, url.length() - 1);
        url = url.replaceAll(" ", "%20");

        try {
            // Create Basic Auth header
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            // Build the HTTP GET request with authorization header
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Basic " + encodedAuth)
                    .GET()
                    .build();

            // Send the request using HttpClient
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Return the response body if successful
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return null; // Non-200 status code
            }
        } catch (Exception e) {
            return null; // Request failed due to an exception
        }
    }
}
