package com.culturacarabobo.sicuc.desktop.services;

import java.time.LocalDate;
import java.util.List;

import com.culturacarabobo.sicuc.desktop.clients.ArtCategoryClient;
import com.culturacarabobo.sicuc.desktop.models.ArtCategoryResponse;
import com.culturacarabobo.sicuc.desktop.utils.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ArtCategoryService {

    // Retrieves and deserializes a list of art categories from the API
    public static List<ArtCategoryResponse> getArtCategories() {
        String json = ArtCategoryClient.getArtCategoriesJson();
        if (json != null) {
            // Configure Gson to handle LocalDate with a custom adapter
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            // Deserialize JSON into a list of ArtCategoryResponse objects
            return gson.fromJson(json, new TypeToken<List<ArtCategoryResponse>>() {
            }.getType());
        } else {
            return null; // Return null if the response is empty
        }
    }

}
