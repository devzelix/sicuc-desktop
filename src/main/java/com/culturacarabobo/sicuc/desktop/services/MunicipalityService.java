package com.culturacarabobo.sicuc.desktop.services;

import java.time.LocalDate;
import java.util.List;

import com.culturacarabobo.sicuc.desktop.clients.MunicipalityClient;
import com.culturacarabobo.sicuc.desktop.models.MunicipalityResponse;
import com.culturacarabobo.sicuc.desktop.utils.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MunicipalityService {

    // Retrieves and deserializes a list of municipalities from the API
    public static List<MunicipalityResponse> getMunicipalities() {
        String json = MunicipalityClient.getMunicipalitiesJson();
        if (json != null) {
            // Configure Gson with custom adapter to handle LocalDate
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            // Deserialize JSON into a list of MunicipalityResponse objects
            return gson.fromJson(json, new TypeToken<List<MunicipalityResponse>>() {
            }.getType());
        } else {
            return null; // Return null if no data received
        }
    }

}
