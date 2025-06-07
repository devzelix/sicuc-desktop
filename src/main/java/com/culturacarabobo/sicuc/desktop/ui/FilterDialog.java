package com.culturacarabobo.sicuc.desktop.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.culturacarabobo.sicuc.desktop.models.ArtCategoryResponse;
import com.culturacarabobo.sicuc.desktop.models.ArtDisciplineResponse;
import com.culturacarabobo.sicuc.desktop.models.MunicipalityResponse;
import com.culturacarabobo.sicuc.desktop.models.ParishResponse;
import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.CursorStyles;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.IdToNameResolver;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.culturacarabobo.sicuc.desktop.utils.InitialDataLoader;
import com.culturacarabobo.sicuc.desktop.utils.NameToIdResolver;
import com.formdev.flatlaf.FlatClientProperties;

public class FilterDialog extends JDialog {

    // Dropdown for municipalities
    private JComboBox<String> MUNICIPALITY_SELECT;
    // Dropdown for parishes
    private JComboBox<String> PARISH_SELECT;
    // Dropdown for art categories
    private JComboBox<String> ART_CATEGORY_SELECT;
    // Dropdown for art disciplines
    private JComboBox<String> ART_DISCIPLINE_SELECT;

    public FilterDialog(JFrame parent, CrudPanel crudPanel, int municipalityId, int parishId, int artCategoryId,
            int artDisciplineId, String gender, String disability, String illness) {
        super();
        setModal(true); // Make dialog modal
        setLayout(null); // Use absolute layout
        setTitle("Filtrar Resultados"); // Set dialog title
        // Set multiple icon sizes for the dialog
        setIconImages(Arrays.asList(ImageResources.ICON_16, ImageResources.ICON_32, ImageResources.ICON_48,
                ImageResources.ICON_64, ImageResources.ICON_128, ImageResources.ICON_256));
        setSize(610, 470); // Set dialog size
        setBackground(ColorPalette.INPUT_COLOR); // Set background color
        setLocationRelativeTo(parent); // Center relative to parent window
        setResizable(false); // Disable resizing
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Dispose on close

        // Label for Municipality dropdown
        JLabel municipalityLabel = new JLabel("Municipio :");
        municipalityLabel.setFont(FontStyles.FONT_TITLE.deriveFont(15f)); // Set font size
        municipalityLabel.setBounds(25, 15, 75, 25); // Set position and size

        // Load municipalities data and create options array with "None" as default
        // first option
        List<MunicipalityResponse> municipalities = InitialDataLoader.getMunicipalities();
        String[] municipalitiesOptions = new String[municipalities.size() + 1];
        municipalitiesOptions[0] = "Ninguno";
        for (int i = 1; i < municipalitiesOptions.length; i++) {
            municipalitiesOptions[i] = municipalities.get(i - 1).getName();
        }

        // Initialize Municipality JComboBox with options
        MUNICIPALITY_SELECT = new JComboBox<>(municipalitiesOptions);
        MUNICIPALITY_SELECT.putClientProperty(FlatClientProperties.STYLE, "arc: 20"); // Style property
        MUNICIPALITY_SELECT.setBounds(25, 45, 260, 35); // Set position and size
        MUNICIPALITY_SELECT.setFont(FontStyles.FONT_TEXT.deriveFont(15f)); // Set font size
        MUNICIPALITY_SELECT.setCursor(CursorStyles.HAND_CURSOR); // Set cursor style

        // Resolve and set selected municipality by ID; default to "Ninguno" if
        // "Carabobo"
        String municipalitySelected = IdToNameResolver.getMunicipality(municipalityId);
        MUNICIPALITY_SELECT.setSelectedItem(municipalitySelected.equals("Carabobo") ? "Ninguno" : municipalitySelected);

        // Add listener to reload parishes when municipality selection changes
        MUNICIPALITY_SELECT.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                loadParishes();
            }
        });

        // Label for Parish dropdown
        JLabel parishLabel = new JLabel("Parroquia :");
        parishLabel.setFont(FontStyles.FONT_TITLE.deriveFont(15f)); // Font size
        parishLabel.setBounds(315, 15, 75, 25); // Position and size

        // Initialize empty Parish JComboBox
        PARISH_SELECT = new JComboBox<>();
        PARISH_SELECT.putClientProperty(FlatClientProperties.STYLE, "arc: 20"); // Style property
        PARISH_SELECT.setBounds(315, 45, 260, 35); // Position and size
        PARISH_SELECT.setFont(FontStyles.FONT_TEXT.deriveFont(15f)); // Font size
        PARISH_SELECT.setCursor(CursorStyles.HAND_CURSOR); // Cursor style

        loadParishes(); // Populate parishes based on current municipality selection

        // Resolve and set selected parish by ID; default to "Ninguna" if none
        String parishSelected = IdToNameResolver.getParish(parishId);
        PARISH_SELECT.setSelectedItem(parishSelected == null ? "Ninguna" : parishSelected);

        // Label for Art Category dropdown
        JLabel artCategoryLabel = new JLabel("Categoría Artística :");
        artCategoryLabel.setFont(FontStyles.FONT_TITLE.deriveFont(15f)); // Font size
        artCategoryLabel.setBounds(25, 105, 130, 25); // Position and size

        // Load art categories data and create options array with "None" as default
        // first option
        List<ArtCategoryResponse> artCategories = InitialDataLoader.getArtCategories();
        String[] artCategoriesOptions = new String[artCategories.size() + 1];
        artCategoriesOptions[0] = "Ninguna";
        for (int i = 1; i < artCategoriesOptions.length; i++) {
            artCategoriesOptions[i] = artCategories.get(i - 1).getName();
        }

        // Initialize Art Category JComboBox with options
        ART_CATEGORY_SELECT = new JComboBox<>(artCategoriesOptions);
        ART_CATEGORY_SELECT.putClientProperty(FlatClientProperties.STYLE, "arc: 20"); // Style property
        ART_CATEGORY_SELECT.setBounds(25, 135, 260, 35); // Position and size
        ART_CATEGORY_SELECT.setFont(FontStyles.FONT_TEXT.deriveFont(15f)); // Font size
        ART_CATEGORY_SELECT.setCursor(CursorStyles.HAND_CURSOR); // Cursor style

        // Resolve and set selected art category by ID; default to "Ninguna" if none
        String artCategorySelected = IdToNameResolver.getArtCategory(artCategoryId);
        ART_CATEGORY_SELECT.setSelectedItem(artCategorySelected == null ? "Ninguna" : artCategorySelected);

        // Add listener to reload art disciplines when art category selection changes
        ART_CATEGORY_SELECT.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                loadArtDisciplines();
            }
        });

        // Label for Art Discipline dropdown
        JLabel artDisciplineLabel = new JLabel("Disciplina Artística :");
        artDisciplineLabel.setFont(FontStyles.FONT_TITLE.deriveFont(15f)); // Font size
        artDisciplineLabel.setBounds(315, 105, 130, 25); // Position and size

        // Initialize empty Art Discipline JComboBox
        ART_DISCIPLINE_SELECT = new JComboBox<>();
        ART_DISCIPLINE_SELECT.putClientProperty(FlatClientProperties.STYLE, "arc: 20"); // Style property
        ART_DISCIPLINE_SELECT.setBounds(315, 135, 260, 35); // Position and size
        ART_DISCIPLINE_SELECT.setFont(FontStyles.FONT_TEXT.deriveFont(15f)); // Font size
        ART_DISCIPLINE_SELECT.setCursor(CursorStyles.HAND_CURSOR); // Cursor style

        loadArtDisciplines(); // Populate disciplines based on current category selection

        // Resolve and set selected art discipline by ID; default to "Ninguna" if none
        String artDisciplineSelected = IdToNameResolver.getArtDiscipline(artDisciplineId);
        ART_DISCIPLINE_SELECT.setSelectedItem(artDisciplineSelected == null ? "Ninguna" : artDisciplineSelected);

        // Label for Gender dropdown
        JLabel genderLabel = new JLabel("Género :");
        genderLabel.setFont(FontStyles.FONT_TITLE.deriveFont(15f)); // Font size
        genderLabel.setBounds(25, 195, 75, 25); // Position and size

        // Gender options array
        String[] gendersOptions = { "Ninguno", "Femenino", "Masculino" };

        // Initialize Gender JComboBox
        JComboBox<String> genderSelect = new JComboBox<>(gendersOptions);
        genderSelect.putClientProperty(FlatClientProperties.STYLE, "arc: 20"); // Style property
        genderSelect.setBounds(25, 225, 260, 35); // Position and size
        genderSelect.setFont(FontStyles.FONT_TEXT.deriveFont(15f)); // Font size
        genderSelect.setCursor(CursorStyles.HAND_CURSOR); // Cursor style

        // Set selected gender based on input value (empty = None, "F" = Female, else
        // Male)
        genderSelect.setSelectedItem(gender == "" ? "Ninguno" : (gender == "F" ? "Femenino" : "Masculino"));

        // Label for Disability dropdown
        JLabel disabilityLabel = new JLabel("Discapacidad :");
        disabilityLabel.setFont(FontStyles.FONT_TITLE.deriveFont(15f)); // Font size
        disabilityLabel.setBounds(315, 195, 95, 25); // Position and size

        // Disability filter options
        String[] disabilitiesOptions = { "No Filtrar", "Con Discapacidad", "Sin Discapacidad" };

        // Initialize Disability JComboBox
        JComboBox<String> disabilitySelect = new JComboBox<>(disabilitiesOptions);
        disabilitySelect.putClientProperty(FlatClientProperties.STYLE, "arc: 20"); // Style property
        disabilitySelect.setBounds(315, 225, 260, 35); // Position and size
        disabilitySelect.setFont(FontStyles.FONT_TEXT.deriveFont(15f)); // Font size
        disabilitySelect.setCursor(CursorStyles.HAND_CURSOR); // Cursor style

        // Set selected disability filter based on input value (empty = No filter,
        // "false" = No disability, else With disability)
        disabilitySelect.setSelectedItem(
                disability == "" ? "No Filtrar" : (disability == "false" ? "Sin Discapacidad" : "Con Discapacidad"));

        // Label and combo box for illness filter
        JLabel illnessLabel = new JLabel("Enfermedad :");
        illnessLabel.setFont(FontStyles.FONT_TITLE.deriveFont(15f));
        illnessLabel.setBounds(170, 285, 95, 25);

        String[] illnessOptions = { "No Filtrar", "Con Enfermedad", "Sin Enfermedad" };

        JComboBox<String> illnessSelect = new JComboBox<>(illnessOptions);
        illnessSelect.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        illnessSelect.setBounds(170, 315, 260, 35);
        illnessSelect.setFont(FontStyles.FONT_TEXT.deriveFont(15f));
        illnessSelect.setCursor(CursorStyles.HAND_CURSOR);
        // Set selected illness based on input parameter
        illnessSelect.setSelectedItem(
                illness == "" ? "No Filtrar" : (illness == "false" ? "Sin Enfermedad" : "Con Enfermedad"));

        // Cancel button configuration and action to close dialog
        JButton cancelButton = new JButton("CANCELAR");
        cancelButton.setForeground(ColorPalette.TEXT_COLOR);
        cancelButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
        cancelButton.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        cancelButton.setBackground(ColorPalette.ERROR_COLOR);
        cancelButton.setBounds(135, 375, 150, 45);
        cancelButton.setCursor(CursorStyles.HAND_CURSOR);
        cancelButton.addActionListener(e -> {
            dispose();
        });

        // Apply button configuration and action to apply filters and reload data
        JButton applyButton = new JButton("APLICAR");
        applyButton.setForeground(ColorPalette.TEXT_COLOR);
        applyButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
        applyButton.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        applyButton.setBackground(ColorPalette.PRIMARY_COLOR);
        applyButton.setBounds(315, 375, 150, 45);
        applyButton.setCursor(CursorStyles.HAND_CURSOR);
        applyButton.addActionListener(e -> {
            // Resolve selected municipality and update filter in CRUD panel
            int munId = NameToIdResolver.getMunicipality(String.valueOf(MUNICIPALITY_SELECT.getSelectedItem()));
            crudPanel.setMunicipalityId(munId);

            // Resolve and set parish filter based on selected municipality and parish
            crudPanel.setParishId(NameToIdResolver.getParish(munId, String.valueOf(PARISH_SELECT.getSelectedItem())));

            // Resolve and set art category filter
            int artCatId = NameToIdResolver.getArtCategory(String.valueOf(ART_CATEGORY_SELECT.getSelectedItem()));
            crudPanel.setArtCategoryId(artCatId);

            // Resolve and set art discipline filter
            crudPanel.setArtDisciplineId(NameToIdResolver.getArtDiscipline(artCatId,
                    String.valueOf(ART_DISCIPLINE_SELECT.getSelectedItem())));

            // Set gender filter with mapping from display string to internal code
            String gen = (String) genderSelect.getSelectedItem();
            crudPanel.setGender(gen.equals("Ninguno") ? "" : (gen.equals("Femenino") ? "F" : "M"));

            // Set disability filter with mapping from display string to internal code
            String dis = (String) disabilitySelect.getSelectedItem();
            crudPanel
                    .setDisability(dis.equals("No Filtrar") ? "" : (dis.equals("Sin Discapacidad") ? "false" : "true"));

            // Set illness filter with mapping from display string to internal code
            String ill = (String) illnessSelect.getSelectedItem();
            crudPanel.setIllness(ill.equals("No Filtrar") ? "" : (ill.equals("Sin Enfermedad") ? "false" : "true"));

            // Clear search and reload data with new filters
            crudPanel.clearSearch();
            crudPanel.loadData("");
            dispose();
        });

        // Panel setup and adding all UI components
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 610, 470);
        panel.setLayout(null);
        panel.add(municipalityLabel);
        panel.add(MUNICIPALITY_SELECT);
        panel.add(parishLabel);
        panel.add(PARISH_SELECT);
        panel.add(artCategoryLabel);
        panel.add(ART_CATEGORY_SELECT);
        panel.add(artDisciplineLabel);
        panel.add(ART_DISCIPLINE_SELECT);
        panel.add(genderLabel);
        panel.add(genderSelect);
        panel.add(disabilityLabel);
        panel.add(disabilitySelect);
        panel.add(illnessLabel);
        panel.add(illnessSelect);
        panel.add(cancelButton);
        panel.add(applyButton);

        getContentPane().add(panel);

        setVisible(true);

        // Request focus for apply button by default
        applyButton.requestFocus();

    }

    // Loads parishes filtered by selected municipality
    private void loadParishes() {
        List<ParishResponse> parishes = InitialDataLoader.getParishes().stream()
                .filter(parish -> parish.getMunicipalityId() == NameToIdResolver
                        .getMunicipality(String.valueOf(MUNICIPALITY_SELECT.getSelectedItem())))
                .collect(Collectors.toList());

        PARISH_SELECT.removeAllItems();
        PARISH_SELECT.addItem("Ninguna");
        for (ParishResponse parish : parishes) {
            PARISH_SELECT.addItem(parish.getName());
        }
    }

    // Loads art disciplines filtered by selected art category
    private void loadArtDisciplines() {
        List<ArtDisciplineResponse> artDisciplines = InitialDataLoader.getArtDisciplines().stream()
                .filter(artDiscipline -> artDiscipline.getArtCategoryId() == NameToIdResolver
                        .getArtCategory(String.valueOf(ART_CATEGORY_SELECT.getSelectedItem())))
                .collect(Collectors.toList());

        ART_DISCIPLINE_SELECT.removeAllItems();
        ART_DISCIPLINE_SELECT.addItem("Ninguna");
        for (ArtDisciplineResponse artDiscipline : artDisciplines) {
            ART_DISCIPLINE_SELECT.addItem(artDiscipline.getName());
        }
    }
}