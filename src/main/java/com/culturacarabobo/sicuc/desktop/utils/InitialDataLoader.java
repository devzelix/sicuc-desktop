package com.culturacarabobo.sicuc.desktop.utils;

import java.util.List;

import com.culturacarabobo.sicuc.desktop.models.*;
import com.culturacarabobo.sicuc.desktop.services.*;

public class InitialDataLoader {

    // Cached list of municipalities
    private static List<MunicipalityResponse> municipalities;
    // Cached list of parishes
    private static List<ParishResponse> parishes;
    // Cached list of art categories
    private static List<ArtCategoryResponse> artCategories;
    // Cached list of art disciplines
    private static List<ArtDisciplineResponse> artDisciplines;
    // Cached list of cultors (filtered by parameters)
    private static List<CultorResponse> cultors;

    // Returns cached municipalities or loads them if null
    public static List<MunicipalityResponse> getMunicipalities() {
        if (municipalities == null) {
            municipalities = MunicipalityService.getMunicipalities();
        }
        return municipalities;
    }

    // Returns cached parishes or loads them if null
    public static List<ParishResponse> getParishes() {
        if (parishes == null) {
            parishes = ParishService.getParishes();
        }
        return parishes;
    }

    // Returns cached art categories or loads them if null
    public static List<ArtCategoryResponse> getArtCategories() {
        if (artCategories == null) {
            artCategories = ArtCategoryService.getArtCategories();
        }
        return artCategories;
    }

    // Returns cached art disciplines or loads them if null
    public static List<ArtDisciplineResponse> getArtDisciplines() {
        if (artDisciplines == null) {
            artDisciplines = ArtDisciplineService.getArtDisciplines();
        }
        return artDisciplines;
    }

    // Loads cultors filtered by the provided parameters (no caching)
    public static List<CultorResponse> getCultors(String username, String password, int municipalityId, int parishId,
            String gender, int artCategoryId, int artDisciplineId, String disability, String illness, String query) {
        cultors = CultorService.getCultors(username, password, municipalityId, parishId, gender, artCategoryId,
                artDisciplineId, disability, illness, query);
        return cultors;
    }

}
