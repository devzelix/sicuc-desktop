package com.culturacarabobo.sicuc.desktop.utils;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;

import com.culturacarabobo.sicuc.desktop.models.CultorResponse;
import com.culturacarabobo.sicuc.desktop.ui.PieChartPanel;

public class PieChartManager {

    private static final Color[] COLORS = { ColorPalette.PRIMARY_COLOR, ColorPalette.PIE_COLOR_2,
            ColorPalette.PIE_COLOR_3,
            ColorPalette.PIE_COLOR_4, ColorPalette.PIE_COLOR_5, ColorPalette.PIE_COLOR_6, ColorPalette.PIE_COLOR_7,
            ColorPalette.PIE_COLOR_8 };

    /**
     * Creates a pie chart grouped by art categories.
     * Returns a customized chart and updates the PieChartPanel state.
     */
    @SuppressWarnings("rawtypes")
    public static JFreeChart customizeByCategories(List<CultorResponse> cultors, PieChartPanel pieChartPanel) {
        if (cultors.size() > 0) {
            List<String> artCategories = new ArrayList<>();
            for (CultorResponse cultor : cultors) {
                String artCategory = IdToNameResolver.getArtCategory(cultor.getArtCategoryId());
                if (!artCategories.contains(artCategory)) {
                    artCategories.add(artCategory);
                }
            }
            artCategories.sort(String.CASE_INSENSITIVE_ORDER);
            DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
            for (String artCategory : artCategories) {
                int count = 0;
                for (CultorResponse cultor : cultors) {
                    String name = IdToNameResolver.getArtCategory(cultor.getArtCategoryId());
                    if (name.equals(artCategory)) {
                        count++;
                    }
                }
                dataset.setValue(artCategory, count);
            }
            JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);
            pieChart.setBackgroundPaint(null);
            pieChart.setBorderVisible(false);
            PiePlot plot = (PiePlot) pieChart.getPlot();
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                    "{2}", new DecimalFormat("0"), new DecimalFormat("0%")));
            plot.setLabelBackgroundPaint(null);
            plot.setLabelOutlinePaint(null);
            plot.setLabelFont(FontCustom.loadFontText().deriveFont(13f));
            plot.setBackgroundPaint(null);
            plot.setLabelShadowPaint(null);
            plot.setOutlineVisible(false);

            for (int i = 0; i < artCategories.size(); i++) {
                plot.setSectionPaint(artCategories.get(i), COLORS[i]);
            }
            LegendTitle legend = pieChart.getLegend();
            legend.setBackgroundPaint(null);
            legend.setItemFont(FontCustom.loadFontText().deriveFont(14f));
            pieChartPanel.setTitle("Cultores Por Categoría");
            pieChartPanel.setIsEmpty(false);
            return pieChart;
        } else {
            // Return empty chart when no data
            DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
            dataset.setValue("", 1);

            JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, false, false, false);
            pieChart.setBackgroundPaint(null);
            pieChart.setBorderVisible(false);
            PiePlot plot = (PiePlot) pieChart.getPlot();
            plot.setLabelGenerator(null);
            plot.setBackgroundPaint(null);
            plot.setOutlineVisible(false);
            plot.setSectionPaint("", new Color(0x636363));
            pieChartPanel.setTitle("Cultores Por Categoría");
            pieChartPanel.setIsEmpty(true);
            return pieChart;
        }
    }

    /**
     * Creates a pie chart grouped by art disciplines.
     * Returns a customized chart and updates the PieChartPanel state.
     */
    @SuppressWarnings("rawtypes")
    public static JFreeChart customizeByDisciplines(List<CultorResponse> cultors, PieChartPanel pieChartPanel) {
        if (cultors.size() > 0) {
            List<String> artDisciplines = new ArrayList<>();
            for (CultorResponse cultor : cultors) {
                String artDiscipline = IdToNameResolver.getArtDiscipline(cultor.getArtDisciplineId());
                if (!artDisciplines.contains(artDiscipline)) {
                    artDisciplines.add(artDiscipline);
                }
            }
            artDisciplines.sort(String.CASE_INSENSITIVE_ORDER);
            DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
            for (String artDiscipline : artDisciplines) {
                int count = 0;
                for (CultorResponse cultor : cultors) {
                    String name = IdToNameResolver.getArtDiscipline(cultor.getArtDisciplineId());
                    if (name.equals(artDiscipline)) {
                        count++;
                    }
                }
                dataset.setValue(artDiscipline, count);
            }
            JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);
            pieChart.setBackgroundPaint(null);
            pieChart.setBorderVisible(false);
            PiePlot plot = (PiePlot) pieChart.getPlot();
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                    "{2}", new DecimalFormat("0"), new DecimalFormat("0%")));
            plot.setLabelBackgroundPaint(null);
            plot.setLabelOutlinePaint(null);
            plot.setLabelFont(FontCustom.loadFontText().deriveFont(13f));
            plot.setBackgroundPaint(null);
            plot.setLabelShadowPaint(null);
            plot.setOutlineVisible(false);

            for (int i = 0; i < artDisciplines.size(); i++) {
                plot.setSectionPaint(artDisciplines.get(i), COLORS[i]);
            }
            LegendTitle legend = pieChart.getLegend();
            legend.setBackgroundPaint(null);
            legend.setItemFont(FontCustom.loadFontText().deriveFont(14f));
            pieChartPanel.setTitle("Cultores Por Disciplina");
            pieChartPanel.setIsEmpty(false);
            return pieChart;
        } else {
            // Return empty chart when no data
            DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
            dataset.setValue("", 1);

            JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, false, false, false);
            pieChart.setBackgroundPaint(null);
            pieChart.setBorderVisible(false);
            PiePlot plot = (PiePlot) pieChart.getPlot();
            plot.setLabelGenerator(null);
            plot.setBackgroundPaint(null);
            plot.setOutlineVisible(false);
            plot.setSectionPaint("", new Color(0x636363));
            pieChartPanel.setTitle("Cultores Por Disciplina");
            pieChartPanel.setIsEmpty(true);
            return pieChart;

        }
    }

}
