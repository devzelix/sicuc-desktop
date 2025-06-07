package com.culturacarabobo.sicuc.desktop.services;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Base64;

import com.culturacarabobo.sicuc.desktop.config.EnvConfig;

public class AuthService {

    // Stores the Basic Auth header for future requests
    private static String authHeader;

    // Base URL for cultors endpoint
    private static final String URL = EnvConfig.getApiUrl() + "/cultors";

    // Attempts to login by sending a GET request with Basic Auth header
    public static int login(String username, String password) {
        try {
            authHeader = createAuthHeader(username, password);
            URL url = (new URI(URL)).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", authHeader);

            // Returns HTTP response code (e.g., 200 if success)
            int responseCode = conn.getResponseCode();
            return responseCode;
        } catch (Exception e) {
            return 500; // Return 500 if an exception occurs
        }
    }

    // Creates the Basic Authorization header from username and password
    public static String createAuthHeader(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    // Returns the stored Authorization header
    public static String getAuthHeader() {
        return authHeader;
    }
}
