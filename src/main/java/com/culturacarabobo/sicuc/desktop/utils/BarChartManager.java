package com.culturacarabobo.sicuc.desktop.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.culturacarabobo.sicuc.desktop.models.CultorResponse;

public class BarChartManager {

    // Creates a bar chart grouped by municipalities with a label for the title
    public static JFreeChart customizeByMunicipalities(List<CultorResponse> cultors, JLabel title) {

        if (cultors.size() > 0) {
            List<String> municipalities = new ArrayList<>();
            // Collect unique municipalities from the cultors list
            for (CultorResponse cultor : cultors) {
                String municipality = IdToNameResolver.getMunicipality(cultor.getMunicipalityId());
                if (!municipalities.contains(municipality)) {
                    municipalities.add(municipality);
                }
            }
            municipalities.sort(String.CASE_INSENSITIVE_ORDER); // Sort municipalities alphabetically

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            // Count cultors per municipality and add to dataset
            for (String municipality : municipalities) {
                int count = 0;
                for (CultorResponse cultor : cultors) {
                    String name = IdToNameResolver.getMunicipality(cultor.getMunicipalityId());
                    if (name.equals(municipality)) {
                        count++;
                    }
                }
                dataset.addValue(count, "Cantidad", municipality);
            }
            // Create the bar chart with the dataset
            JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL,
                    false, true, false);
            barChart.setBackgroundPaint(null);

            CategoryPlot plot = (CategoryPlot) barChart.getPlot();
            plot.setBackgroundPaint(null);
            plot.setOutlineVisible(false);
            plot.setRangeGridlinePaint(ColorPalette.TERTIARY_COLOR);

            // Set bar color
            ((BarRenderer) (plot.getRenderer())).setSeriesPaint(0, ColorPalette.PRIMARY_COLOR);

            // Configure X axis (category axis)
            CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
            xAxis.setAxisLinePaint(ColorPalette.TERTIARY_COLOR);
            xAxis.setTickMarksVisible(false);
            xAxis.setTickLabelsVisible(false);

            // Configure Y axis (value axis)
            ValueAxis yAxis = (ValueAxis) plot.getRangeAxis();
            yAxis.setAxisLinePaint(ColorPalette.TERTIARY_COLOR);
            yAxis.setTickMarkPaint(ColorPalette.TERTIARY_COLOR);
            yAxis.setTickLabelFont(FontStyles.FONT_TEXT.deriveFont(9f));

            // Set chart title text in the JLabel
            title.setText("Cultores Por Municipio");

            return barChart;
        } else {
            // If no data, create an empty dataset with a placeholder
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(0, "", "VACIO");

            JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL,
                    false, false, false);
            barChart.setBackgroundPaint(null);

            CategoryPlot plot = (CategoryPlot) barChart.getPlot();
            plot.setBackgroundPaint(null);
            plot.setOutlineVisible(false);
            plot.setRangeGridlinePaint(ColorPalette.TERTIARY_COLOR);

            ((BarRenderer) (plot.getRenderer())).setSeriesPaint(0, ColorPalette.PRIMARY_COLOR);

            CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
            xAxis.setAxisLinePaint(ColorPalette.TERTIARY_COLOR);
            xAxis.setTickMarkPaint(ColorPalette.TERTIARY_COLOR);
            xAxis.setTickLabelFont(FontStyles.FONT_TEXT.deriveFont(15f));

            ValueAxis yAxis = (ValueAxis) plot.getRangeAxis();
            yAxis.setAxisLinePaint(ColorPalette.TERTIARY_COLOR);
            yAxis.setTickMarkPaint(ColorPalette.TERTIARY_COLOR);
            yAxis.setTickMarksVisible(false);
            yAxis.setTickLabelsVisible(false);

            title.setText("Cultores Por Municipio");
            return barChart;
        }

    }

    // Creates a bar chart grouped by parishes with a label for the title
    public static JFreeChart customizeByParishes(List<CultorResponse> cultors, JLabel title) {

        if (cultors.size() > 0) {
            List<String> parishes = new ArrayList<>();
            // Collect unique parishes from the cultors list
            for (CultorResponse cultor : cultors) {
                String parish = IdToNameResolver.getParish(cultor.getParishId());
                if (!parishes.contains(parish)) {
                    parishes.add(parish);
                }
            }
            parishes.sort(String.CASE_INSENSITIVE_ORDER); // Sort parishes alphabetically

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            // Count cultors per parish and add to dataset
            for (String parish : parishes) {
                int count = 0;
                for (CultorResponse cultor : cultors) {
                    String name = IdToNameResolver.getParish(cultor.getParishId());
                    if (name.equals(parish)) {
                        count++;
                    }
                }
                dataset.addValue(count, "Cantidad", parish);
            }
            // Create the bar chart with the dataset
            JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL,
                    false, true, false);
            barChart.setBackgroundPaint(null);

            CategoryPlot plot = (CategoryPlot) barChart.getPlot();
            plot.setBackgroundPaint(null);
            plot.setOutlineVisible(false);
            plot.setRangeGridlinePaint(ColorPalette.TERTIARY_COLOR);

            // Set bar color
            ((BarRenderer) (plot.getRenderer())).setSeriesPaint(0, ColorPalette.PRIMARY_COLOR);

            // Configure X axis (category axis)
            CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
            xAxis.setAxisLinePaint(ColorPalette.TERTIARY_COLOR);
            xAxis.setTickMarksVisible(false);
            xAxis.setTickLabelsVisible(false);

            // Configure Y axis (value axis)
            ValueAxis yAxis = (ValueAxis) plot.getRangeAxis();
            yAxis.setAxisLinePaint(ColorPalette.TERTIARY_COLOR);
            yAxis.setTickMarkPaint(ColorPalette.TERTIARY_COLOR);
            yAxis.setTickLabelFont(FontStyles.FONT_TEXT.deriveFont(9f));

            // Set chart title text in the JLabel
            title.setText("Cultores Por Parroquia");

            return barChart;
        } else {
            // If no data, create an empty dataset with a placeholder
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(0, "", "VACIO");

            JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL,
                    false, false, false);
            barChart.setBackgroundPaint(null);

            CategoryPlot plot = (CategoryPlot) barChart.getPlot();
            plot.setBackgroundPaint(null);
            plot.setOutlineVisible(false);
            plot.setRangeGridlinePaint(ColorPalette.TERTIARY_COLOR);

            ((BarRenderer) (plot.getRenderer())).setSeriesPaint(0, ColorPalette.PRIMARY_COLOR);

            CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
            xAxis.setAxisLinePaint(ColorPalette.TERTIARY_COLOR);
            xAxis.setTickMarkPaint(ColorPalette.TERTIARY_COLOR);
            xAxis.setTickLabelFont(FontStyles.FONT_TEXT.deriveFont(15f));

            ValueAxis yAxis = (ValueAxis) plot.getRangeAxis();
            yAxis.setAxisLinePaint(ColorPalette.TERTIARY_COLOR);
            yAxis.setTickMarkPaint(ColorPalette.TERTIARY_COLOR);
            yAxis.setTickMarksVisible(false);
            yAxis.setTickLabelsVisible(false);

            title.setText("Cultores Por Parroquia");
            return barChart;
        }

    }

}
