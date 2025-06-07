package com.culturacarabobo.sicuc.desktop.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    // Loads environment variables from a .env file, ignoring if it's missing
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    // Returns the value of an environment variable by key
    public static String get(String key) {
        return dotenv.get(key);
    }

    // Returns the base API URL from the environment configuration
    public static String getApiUrl() {
        return get("API_URL");
    }
}
