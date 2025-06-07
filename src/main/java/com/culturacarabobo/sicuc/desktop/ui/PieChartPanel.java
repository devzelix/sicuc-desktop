package com.culturacarabobo.sicuc.desktop.ui;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import com.culturacarabobo.sicuc.desktop.models.CultorResponse;
import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.culturacarabobo.sicuc.desktop.utils.InsetsConstants;
import com.culturacarabobo.sicuc.desktop.utils.PieChartManager;
import com.formdev.flatlaf.ui.FlatLineBorder;

public class PieChartPanel extends JPanel {

    private final JLabel TITLE; // Title label
    private boolean isEmpty; // Flag to indicate if chart data is empty
    private final JLabel EMPTY; // Label shown when no data is available
    private final ChartPanel CHART_PANEL; // Panel that contains the pie chart
    private final JLabel LOADER; // Loading icon label

    public PieChartPanel() {
        super();
        setLayout(null);
        setBorder(new FlatLineBorder(InsetsConstants.NO_PADDING, ColorPalette.PRIMARY_COLOR, 5, 60));
        setBackground(null);

        TITLE = new JLabel("Cultores Por Categoría"); // Default title
        TITLE.setFont(FontStyles.FONT_TITLE.deriveFont(25f));
        TITLE.setBounds(43, 5, 245, 35);
        TITLE.setHorizontalAlignment(JLabel.CENTER);
        add(TITLE);

        EMPTY = new JLabel("VACÍO"); // Text shown when no data is present
        EMPTY.setFont(FontStyles.FONT_TEXT.deriveFont(20f));
        EMPTY.setForeground(ColorPalette.TEXT_COLOR);
        EMPTY.setBounds(140, 110, 55, 20);
        add(EMPTY);

        CHART_PANEL = new ChartPanel(null); // Empty chart initially
        CHART_PANEL.setOpaque(false);
        CHART_PANEL.setBounds(12, 48, 310, 155);
        add(CHART_PANEL);

        LOADER = new JLabel(ImageResources.LOADER_ICON_100); // Loader icon, hidden by default
        LOADER.setVisible(false);
        LOADER.setBounds(117, 72, 100, 100);
        add(LOADER);
    }

    public void showLoader() {
        EMPTY.setVisible(false); // Hide empty message
        CHART_PANEL.setVisible(false); // Hide chart
        LOADER.setVisible(true); // Show loader icon
    }

    public void disguiseLoader() {
        LOADER.setVisible(false); // Hide loader icon
        EMPTY.setVisible(isEmpty); // Show empty message if data is empty
        CHART_PANEL.setVisible(true); // Show chart
    }

    public void customizeByCategories(List<CultorResponse> cultors) {
        CHART_PANEL.setChart(PieChartManager.customizeByCategories(cultors, this)); // Update chart by categories
    }

    public void customizeByDisciplines(List<CultorResponse> cultors) {
        CHART_PANEL.setChart(PieChartManager.customizeByDisciplines(cultors, this)); // Update chart by disciplines
    }

    public void setTitle(String title) {
        TITLE.setText(title); // Set title text
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty; // Set empty flag
    }
}
