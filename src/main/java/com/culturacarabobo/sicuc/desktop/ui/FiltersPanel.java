package com.culturacarabobo.sicuc.desktop.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.culturacarabobo.sicuc.desktop.models.ArtCategoryResponse;
import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.CursorStyles;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.culturacarabobo.sicuc.desktop.utils.InitialDataLoader;
import com.culturacarabobo.sicuc.desktop.utils.InsetsConstants;
import com.culturacarabobo.sicuc.desktop.utils.NameToIdResolver;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatLineBorder;

public class FiltersPanel extends JPanel {

    public FiltersPanel(DashboardPanel dashboardPanel, CrudPanel crudPanel, MenuPanel menuPanel) {
        super();
        setLayout(null);
        // Set a custom border with no padding and a secondary color
        setBorder(new FlatLineBorder(InsetsConstants.NO_PADDING, ColorPalette.SECONDARY_COLOR, 1, 40));
        // Set background color for the panel
        setBackground(ColorPalette.SECONDARY_COLOR);

        // Label for Gender filter
        JLabel genderLabel = new JLabel("Género :");
        genderLabel.setFont(FontStyles.FONT_TITLE.deriveFont(17f));
        genderLabel.setForeground(ColorPalette.TEXT_COLOR);
        genderLabel.setBounds(20, 5, 145, 25);
        add(genderLabel);

        // Options for gender JComboBox
        String[] gendersOptions = { "Ninguno", "Femenino", "Masculino" };

        // JComboBox for gender selection
        JComboBox<String> genderSelect = new JComboBox<>(gendersOptions);
        genderSelect.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        genderSelect.setBounds(20, 35, 220, 35);
        genderSelect.setFont(FontStyles.FONT_TEXT.deriveFont(16f));
        genderSelect.setCursor(CursorStyles.HAND_CURSOR);
        // Listen for item selection changes to update filters in DashboardPanel
        genderSelect.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String gender = (String) e.getItem();
                    dashboardPanel.setGender(
                            gender.equals("Ninguno") ? "" : (gender.equals("Femenino") ? "F" : "M"));
                    dashboardPanel.loadData();
                    dashboardPanel.restartTimer();
                }
            }
        });
        add(genderSelect);

        // Label for Art Category filter
        JLabel artCategoryLabel = new JLabel("Categoría Artística :");
        artCategoryLabel.setForeground(ColorPalette.TEXT_COLOR);
        artCategoryLabel.setFont(FontStyles.FONT_TITLE.deriveFont(17f));
        artCategoryLabel.setBounds(260, 5, 145, 25);
        add(artCategoryLabel);

        // Load art categories from initial data loader
        List<ArtCategoryResponse> artCategories = InitialDataLoader.getArtCategories();
        String[] artCategoriesOptions = new String[artCategories.size() + 1];
        artCategoriesOptions[0] = "Ninguna"; // Default option for no filter
        for (int i = 1; i < artCategoriesOptions.length; i++) {
            artCategoriesOptions[i] = artCategories.get(i - 1).getName();
        }

        // JComboBox for Art Category selection
        JComboBox<String> artCategorySelect = new JComboBox<>(artCategoriesOptions);
        artCategorySelect.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        artCategorySelect.setBounds(260, 35, 220, 35);
        artCategorySelect.setFont(FontStyles.FONT_TEXT.deriveFont(16f));
        artCategorySelect.setCursor(CursorStyles.HAND_CURSOR);
        // Listen for item selection changes to update filters in DashboardPanel
        artCategorySelect.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String artCategorySelected = (String) e.getItem();
                    dashboardPanel.setArtCategoryId(NameToIdResolver.getArtCategory(artCategorySelected));
                    dashboardPanel.loadData();
                    dashboardPanel.restartTimer();
                }
            }
        });
        add(artCategorySelect);

        // Button to apply filters and see results
        JButton refreshButton = new JButton("VER RESULTADOS");
        refreshButton.setIcon(ImageResources.SEARCH_ICON);
        refreshButton.setIconTextGap(10);
        refreshButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
        refreshButton.setForeground(ColorPalette.TEXT_COLOR);
        refreshButton.setBackground(ColorPalette.PRIMARY_COLOR);
        refreshButton.putClientProperty(FlatClientProperties.STYLE, "arc: 35");
        refreshButton.setBounds(530, 14, 195, 50);
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                refreshButton.setCursor(CursorStyles.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                refreshButton.setCursor(CursorStyles.DEFAULT_CURSOR);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Sync current dashboard filters into crudPanel before loading data
                crudPanel.setMunicipalityId(dashboardPanel.getMunicipalityId());
                crudPanel.setParishId(0);
                crudPanel.setGender(dashboardPanel.getGender());
                crudPanel.setArtCategoryId(dashboardPanel.getArtCategoryId());
                crudPanel.setArtDisciplineId(0);
                crudPanel.setDisability("");
                crudPanel.setIllness("");
                crudPanel.clearSearch();
                crudPanel.loadData("");
                dashboardPanel.restartTimer();

                // Switch menu panel to "management" tab
                menuPanel.setTabSelected("management");
            }
        });
        add(refreshButton);

        // Button to reset filters and reload data without filters
        JButton resetButton = new JButton("LIMPIAR FILTROS");
        resetButton.setIcon(ImageResources.RESET_FILTERS_ICON);
        resetButton.setIconTextGap(10);
        resetButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
        resetButton.setForeground(ColorPalette.TEXT_COLOR);
        resetButton.setBackground(ColorPalette.PRIMARY_COLOR);
        resetButton.putClientProperty(FlatClientProperties.STYLE, "arc: 35");
        resetButton.setBounds(745, 14, 195, 50);
        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                resetButton.setCursor(CursorStyles.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resetButton.setCursor(CursorStyles.DEFAULT_CURSOR);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Reset selected filters to default values
                genderSelect.setSelectedItem("Ninguno");
                artCategorySelect.setSelectedItem("Ninguna");
                dashboardPanel.resetMunicipalitySelected();
                dashboardPanel.loadData();
                dashboardPanel.restartTimer();
            }
        });
        add(resetButton);
    }

}
