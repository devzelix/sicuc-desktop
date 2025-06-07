package com.culturacarabobo.sicuc.desktop.services;

import java.time.LocalDate;
import java.util.List;

import com.culturacarabobo.sicuc.desktop.clients.ParishClient;
import com.culturacarabobo.sicuc.desktop.models.ParishResponse;
import com.culturacarabobo.sicuc.desktop.utils.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ParishService {

    // Fetches and deserializes a list of parishes from the API
    public static List<ParishResponse> getParishes() {
        String json = ParishClient.getParishesJson();
        if (json != null) {
            // Setup Gson with LocalDate adapter for proper date deserialization
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            // Convert JSON into list of ParishResponse objects
            return gson.fromJson(json, new TypeToken<List<ParishResponse>>() {
            }.getType());
        } else {
            return null; // Return null if no response received
        }
    }

}
