package com.culturacarabobo.sicuc.desktop.utils;

import java.time.LocalDate;
import java.util.List;

import com.culturacarabobo.sicuc.desktop.models.CultorResponse;

public class CultorsStats {

    /**
     * Returns the count of cultors created in the current month.
     * 
     * @param cultors List of CultorResponse objects to analyze
     * @return number of cultors created in the current month
     */
    public static int getMonthlyCultorSummary(List<CultorResponse> cultors) {

        LocalDate today = LocalDate.now(); // Get today's date
        int count = 0; // Initialize counter

        // Loop through all cultors
        for (CultorResponse cultor : cultors) {
            // Check if the creation month matches the current month
            if (cultor.getCreatedAt().getMonth() == today.getMonth()) {
                count++;
            }
        }

        return count; // Return the total count
    }

}
