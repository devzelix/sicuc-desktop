package com.culturacarabobo.sicuc.desktop;

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.culturacarabobo.sicuc.desktop.ui.NetworkErrorDialog;
import com.culturacarabobo.sicuc.desktop.ui.Window;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.culturacarabobo.sicuc.desktop.utils.InitialDataLoader;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
        try {
            // Set the FlatLightLaf look and feel for consistent UI styling
            UIManager.setLookAndFeel(new FlatLightLaf());

            // Loop until initial data is successfully loaded
            while (InitialDataLoader.getMunicipalities() == null || InitialDataLoader.getParishes() == null
                    || InitialDataLoader.getArtCategories() == null
                    || InitialDataLoader.getArtDisciplines() == null) {

                // Create a minimal, invisible frame to own the error dialog
                JFrame temporalFrame = new JFrame();
                temporalFrame.setTitle("Error de conexión");
                temporalFrame.setIconImages(
                        Arrays.asList(ImageResources.ICON_16, ImageResources.ICON_32, ImageResources.ICON_48,
                                ImageResources.ICON_64, ImageResources.ICON_128, ImageResources.ICON_256));
                temporalFrame.setUndecorated(true);
                temporalFrame.setSize(0, 0);
                temporalFrame.setLocationRelativeTo(null);
                temporalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                temporalFrame.setVisible(true);

                // Show network error dialog to inform user and possibly retry
                new NetworkErrorDialog(temporalFrame);

                // Dispose the temporary frame after dialog is handled
                temporalFrame.dispose();
            }

            // Once data loaded, launch main application window
            Window window = new Window();
            window.setVisible(true);

        } catch (Exception ex) {
            // Ideally log or handle the exception here
            ex.printStackTrace();
        }
    }
}
