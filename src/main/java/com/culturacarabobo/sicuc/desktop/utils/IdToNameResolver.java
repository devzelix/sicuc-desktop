package com.culturacarabobo.sicuc.desktop.utils;

import com.culturacarabobo.sicuc.desktop.models.ArtCategoryResponse;
import com.culturacarabobo.sicuc.desktop.models.ArtDisciplineResponse;
import com.culturacarabobo.sicuc.desktop.models.MunicipalityResponse;
import com.culturacarabobo.sicuc.desktop.models.ParishResponse;

public class IdToNameResolver {

    // Returns the municipality name for a given ID.
    // If no matching municipality is found, returns "Carabobo" as default.
    public static String getMunicipality(int id) {
        for (MunicipalityResponse municipality : InitialDataLoader.getMunicipalities()) {
            if (municipality.getId() == id) {
                return municipality.getName();
            }
        }
        return "Carabobo";
    }

    // Returns the parish name for a given ID.
    // Returns null if no matching parish is found.
    public static String getParish(int id) {
        for (ParishResponse parish : InitialDataLoader.getParishes()) {
            if (parish.getId() == id) {
                return parish.getName();
            }
        }
        return null;
    }

    // Returns the art category name for a given ID.
    // Returns null if no matching category is found.
    public static String getArtCategory(int id) {
        for (ArtCategoryResponse artCategory : InitialDataLoader.getArtCategories()) {
            if (artCategory.getId() == id) {
                return artCategory.getName();
            }
        }
        return null;
    }

    // Returns the art discipline name for a given ID.
    // Returns null if no matching discipline is found.
    public static String getArtDiscipline(int id) {
        for (ArtDisciplineResponse artDiscipline : InitialDataLoader.getArtDisciplines()) {
            if (artDiscipline.getId() == id) {
                return artDiscipline.getName();
            }
        }
        return null;
    }

}
