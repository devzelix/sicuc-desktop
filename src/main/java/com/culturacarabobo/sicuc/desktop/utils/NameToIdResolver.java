package com.culturacarabobo.sicuc.desktop.utils;

import com.culturacarabobo.sicuc.desktop.models.ArtCategoryResponse;
import com.culturacarabobo.sicuc.desktop.models.ArtDisciplineResponse;
import com.culturacarabobo.sicuc.desktop.models.MunicipalityResponse;
import com.culturacarabobo.sicuc.desktop.models.ParishResponse;

public class NameToIdResolver {

    // Get municipality ID by its name
    public static int getMunicipality(String name) {
        for (MunicipalityResponse municipality : InitialDataLoader.getMunicipalities()) {
            if (municipality.getName().equals(name)) {
                return municipality.getId();
            }
        }
        return 0; // Return 0 if not found
    }

    // Get parish ID by municipality ID and parish name
    public static int getParish(int municipalityId, String name) {
        for (ParishResponse parish : InitialDataLoader.getParishes()) {
            if (municipalityId == parish.getMunicipalityId() && parish.getName().equals(name)) {
                return parish.getId();
            }
        }
        return 0; // Return 0 if not found
    }

    // Get art category ID by name
    public static int getArtCategory(String name) {
        for (ArtCategoryResponse artCategory : InitialDataLoader.getArtCategories()) {
            if (artCategory.getName().equals(name)) {
                return artCategory.getId();
            }
        }
        return 0; // Return 0 if not found
    }

    // Get art discipline ID by art category ID and discipline name
    public static int getArtDiscipline(int artCategoryId, String name) {
        for (ArtDisciplineResponse artDiscipline : InitialDataLoader.getArtDisciplines()) {
            if (artCategoryId == artDiscipline.getArtCategoryId() && artDiscipline.getName().equals(name)) {
                return artDiscipline.getId();
            }
        }
        return 0; // Return 0 if not found
    }

}
