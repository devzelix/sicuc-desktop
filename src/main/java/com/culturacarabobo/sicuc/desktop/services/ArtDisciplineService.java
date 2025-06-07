package com.culturacarabobo.sicuc.desktop.services;

import java.time.LocalDate;
import java.util.List;

import com.culturacarabobo.sicuc.desktop.clients.ArtDisciplineClient;
import com.culturacarabobo.sicuc.desktop.models.ArtDisciplineResponse;
import com.culturacarabobo.sicuc.desktop.utils.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ArtDisciplineService {

    // Retrieves and deserializes a list of art disciplines from the API
    public static List<ArtDisciplineResponse> getArtDisciplines() {
        String json = ArtDisciplineClient.getArtDisciplinesJson();
        if (json != null) {
            // Configure Gson with custom adapter for LocalDate
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            // Deserialize JSON into list of ArtDisciplineResponse objects
            return gson.fromJson(json, new TypeToken<List<ArtDisciplineResponse>>() {
            }.getType());
        } else {
            return null; // Return null if no data was received
        }
    }

}
