package com.culturacarabobo.sicuc.desktop.clients;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.culturacarabobo.sicuc.desktop.config.EnvConfig;

public class ArtCategoryClient {
    // Base URL for the art categories endpoint
    private static final String BASE_URL = EnvConfig.getApiUrl() + "/art-categories";

    // Sends a GET request to retrieve art categories as a JSON string
    public static String getArtCategoriesJson() {
        try {
            // Build the HTTP GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();

            // Create the HTTP client and send the request
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
