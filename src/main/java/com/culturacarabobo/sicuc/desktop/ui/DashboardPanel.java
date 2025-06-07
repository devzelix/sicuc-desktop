package com.culturacarabobo.sicuc.desktop.ui;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.culturacarabobo.sicuc.desktop.models.CultorResponse;
import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.CultorsStats;
import com.culturacarabobo.sicuc.desktop.utils.InitialDataLoader;

public class DashboardPanel extends JPanel {

    // Reference to main application window
    private final Window WINDOW;

    // Filters for data retrieval
    private int municipalityId;
    private String gender;
    private int artCategoryId;

    // Background workers for data operations
    private SwingWorker<Void, Void> refreshDataWorker;
    private SwingWorker<Void, Void> loadDataWorker;

    // UI components of the dashboard
    private final TotalPanel TOTAL_PANEL;
    private final MonthlyCultorSummaryPanel MONTHLY_CULTOR_SUMMARY_PANEL;
    private final PieChartPanel PIE_CHART_PANEL;
    private final CaraboboMapPanel CARABOBO_MAP_PANEL;
    private final BarChartPanel BAR_CHART_PANEL;
    private final FiltersPanel FILTERS_PANEL;

    // Constructor initializes layout, components, and default filter values
    public DashboardPanel(Window window, CrudPanel crudPanel, MenuPanel menuPanel) {
        super();

        setLayout(null);
        setBackground(ColorPalette.INPUT_COLOR);

        WINDOW = window;
        municipalityId = 0;
        gender = "";
        artCategoryId = 0;

        // Hamburger menu button setup
        HamburgerMenuButton hamburgerMenuButton = new HamburgerMenuButton(WINDOW);
        hamburgerMenuButton.setBounds(10, 10, 49, 49);
        add(hamburgerMenuButton);

        // Initialize and position main dashboard panels
        TOTAL_PANEL = new TotalPanel();
        TOTAL_PANEL.setBounds(70, 15, 249, 186);
        add(TOTAL_PANEL);

        MONTHLY_CULTOR_SUMMARY_PANEL = new MonthlyCultorSummaryPanel();
        MONTHLY_CULTOR_SUMMARY_PANEL.setBounds(330, 15, 304, 186);
        add(MONTHLY_CULTOR_SUMMARY_PANEL);

        PIE_CHART_PANEL = new PieChartPanel();
        PIE_CHART_PANEL.setBounds(645, 15, 334, 215);
        add(PIE_CHART_PANEL);

        CARABOBO_MAP_PANEL = new CaraboboMapPanel(this);
        CARABOBO_MAP_PANEL.setBounds(70, 218, 422, 355);
        add(CARABOBO_MAP_PANEL);

        BAR_CHART_PANEL = new BarChartPanel();
        BAR_CHART_PANEL.setBounds(503, 247, 476, 326);
        add(BAR_CHART_PANEL);

        FILTERS_PANEL = new FiltersPanel(this, crudPanel, menuPanel);
        FILTERS_PANEL.setBounds(20, 590, 960, 95);
        add(FILTERS_PANEL);

    }

    // Refresh data asynchronously without showing loading UI
    public void refreshData() {
        refreshDataWorker = new SwingWorker<>() {

            private List<CultorResponse> cultors;

            @Override
            protected Void doInBackground() {
                cultors = null;
                int i = 0;

                do {
                    if (isCancelled())
                        return null;

                    // Fetch cultors data based on current filters
                    cultors = InitialDataLoader.getCultors(WINDOW.getUsername(), WINDOW.getPassword(),
                            municipalityId, 0, gender,
                            artCategoryId, 0, "", "", "");

                    if (isCancelled())
                        return null;

                    // Handle network error and timer control
                    if (cultors == null) {
                        if (i == 0) {
                            WINDOW.stopTimer();
                            i++;
                        }
                        new NetworkErrorDialog(WINDOW);
                    } else {
                        if (i > 0) {
                            restartTimer();
                        }
                    }
                } while (cultors == null);

                return null;
            }

            @Override
            protected void done() {
                if (isCancelled())
                    return;

                // Update UI components with fetched data
                TOTAL_PANEL.setTotal(String.valueOf(cultors.size()));

                MONTHLY_CULTOR_SUMMARY_PANEL.setTotal(String.valueOf(CultorsStats.getMonthlyCultorSummary(cultors)));

                if (artCategoryId == 0) {
                    PIE_CHART_PANEL.customizeByCategories(cultors);
                } else {
                    PIE_CHART_PANEL.customizeByDisciplines(cultors);
                }

                if (municipalityId == 0) {
                    BAR_CHART_PANEL.customizeByMunicipalities(cultors);
                } else {
                    BAR_CHART_PANEL.customizeByParishes(cultors);
                }
            }
        };
        refreshDataWorker.execute();
    }

    // Load data asynchronously with loading indicators and timer control
    public void loadData() {
        cancelDataWorkers();

        loadDataWorker = new SwingWorker<>() {

            private List<CultorResponse> cultors;

            @Override
            protected Void doInBackground() {
                WINDOW.stopTimer();

                // Show loading indicators on UI panels
                TOTAL_PANEL.showLoader();
                MONTHLY_CULTOR_SUMMARY_PANEL.showLoader();
                PIE_CHART_PANEL.showLoader();
                BAR_CHART_PANEL.showLoader();

                cultors = null;
                int i = 0;

                do {
                    if (isCancelled())
                        return null;

                    // Fetch cultors data with current filters
                    cultors = InitialDataLoader.getCultors(WINDOW.getUsername(), WINDOW.getPassword(),
                            municipalityId, 0, gender,
                            artCategoryId, 0, "", "", "");

                    if (isCancelled())
                        return null;

                    // Handle network errors and timer control
                    if (cultors == null) {
                        if (i == 0) {
                            WINDOW.stopTimer();
                            i++;
                        }
                        new NetworkErrorDialog(WINDOW);
                    } else {
                        if (i > 0) {
                            restartTimer();
                        }
                    }
                } while (cultors == null);

                return null;
            }

            @Override
            protected void done() {
                if (isCancelled())
                    return;

                // Update UI components with fetched data
                TOTAL_PANEL.setTotal(String.valueOf(cultors.size()));

                MONTHLY_CULTOR_SUMMARY_PANEL.setTotal(String.valueOf(CultorsStats.getMonthlyCultorSummary(cultors)));

                if (artCategoryId == 0) {
                    PIE_CHART_PANEL.customizeByCategories(cultors);
                } else {
                    PIE_CHART_PANEL.customizeByDisciplines(cultors);
                }

                if (municipalityId == 0) {
                    BAR_CHART_PANEL.customizeByMunicipalities(cultors);
                } else {
                    BAR_CHART_PANEL.customizeByParishes(cultors);
                }

                // Hide loading indicators and restart timer
                TOTAL_PANEL.disguiseLoader();
                MONTHLY_CULTOR_SUMMARY_PANEL.disguiseLoader();
                PIE_CHART_PANEL.disguiseLoader();
                BAR_CHART_PANEL.disguiseLoader();
                WINDOW.restartTimer();
            }
        };
        loadDataWorker.execute();
    }

    // Cancel ongoing data fetching tasks if any
    public void cancelDataWorkers() {
        if (refreshDataWorker != null)
            refreshDataWorker.cancel(true);

        if (loadDataWorker != null)
            loadDataWorker.cancel(true);
    }

    // Restart the main application timer
    public void restartTimer() {
        WINDOW.restartTimer();
    }

    // Reset selected municipality in map panel
    public void resetMunicipalitySelected() {
        CARABOBO_MAP_PANEL.resetMunicipalitySelected();
    }

    // Getters and setters for filter properties

    public int getMunicipalityId() {
        return municipalityId;
    }

    public String getGender() {
        return gender;
    }

    public int getArtCategoryId() {
        return artCategoryId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setArtCategoryId(int artCategoryId) {
        this.artCategoryId = artCategoryId;
    }

}
