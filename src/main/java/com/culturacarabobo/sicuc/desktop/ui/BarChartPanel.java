package com.culturacarabobo.sicuc.desktop.ui;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import com.culturacarabobo.sicuc.desktop.models.CultorResponse;
import com.culturacarabobo.sicuc.desktop.utils.BarChartManager;
import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.culturacarabobo.sicuc.desktop.utils.InsetsConstants;
import com.formdev.flatlaf.ui.FlatLineBorder;

public class BarChartPanel extends JPanel {

    private final JLabel TITLE;
    private final ChartPanel CHART_PANEL;
    private final JLabel LOADER;

    public BarChartPanel() {
        super();
        setLayout(null);
        // Set border with primary color and custom insets
        setBorder(new FlatLineBorder(InsetsConstants.NO_PADDING, ColorPalette.PRIMARY_COLOR, 5, 60));
        setBackground(null);

        // Title label configuration
        TITLE = new JLabel("Cultores Por Municipio");
        TITLE.setFont(FontStyles.FONT_TITLE.deriveFont(25f));
        TITLE.setBounds(113, 5, 250, 35);
        TITLE.setHorizontalAlignment(JLabel.CENTER);
        add(TITLE);

        // Chart panel initialization (empty initially)
        CHART_PANEL = new ChartPanel(null);
        CHART_PANEL.setOpaque(false);
        CHART_PANEL.setBounds(5, 40, 470, 273);
        add(CHART_PANEL);

        // Loader label with icon, hidden by default
        LOADER = new JLabel(ImageResources.LOADER_ICON_200);
        LOADER.setVisible(false);
        LOADER.setBounds(138, 78, 200, 200);
        add(LOADER);
    }

    // Shows loader and hides chart panel while loading data
    public void showLoader() {
        CHART_PANEL.setVisible(false);
        LOADER.setVisible(true);
    }

    // Hides loader and shows chart panel after loading
    public void disguiseLoader() {
        LOADER.setVisible(false);
        CHART_PANEL.setVisible(true);
    }

    // Sets up bar chart grouped by municipalities with provided data
    public void customizeByMunicipalities(List<CultorResponse> cultors) {
        CHART_PANEL.setChart(BarChartManager.customizeByMunicipalities(cultors, TITLE));
    }

    // Sets up bar chart grouped by parishes with provided data
    public void customizeByParishes(List<CultorResponse> cultors) {
        CHART_PANEL.setChart(BarChartManager.customizeByParishes(cultors, TITLE));
    }

}
