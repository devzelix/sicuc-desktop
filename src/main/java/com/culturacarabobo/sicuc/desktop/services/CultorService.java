package com.culturacarabobo.sicuc.desktop.services;

import com.culturacarabobo.sicuc.desktop.clients.CultorClient;
import com.culturacarabobo.sicuc.desktop.models.CultorResponse;
import com.culturacarabobo.sicuc.desktop.utils.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDate;
import java.util.List;

public class CultorService {

    // Retrieves and deserializes a list of cultors filtered by parameters with
    // Basic Auth
    public static List<CultorResponse> getCultors(String username, String password, int municipalityId, int parishId,
            String gender, int artCategoryId, int artDisciplineId, String disability, String illness, String query) {

        // Fetch JSON from the client with authentication and filters
        String json = CultorClient.getCultorsJson(username, password, municipalityId, parishId, gender, artCategoryId,
                artDisciplineId, disability, illness, query);

        if (json != null) {
            // Configure Gson with LocalDate adapter for deserialization
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            // Deserialize JSON into list of CultorResponse objects
            return gson.fromJson(json, new TypeToken<List<CultorResponse>>() {
            }.getType());
        } else {
            return null; // Return null if no data was received
        }
    }
}
