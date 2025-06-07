package com.culturacarabobo.sicuc.desktop.ui;

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import com.culturacarabobo.sicuc.desktop.utils.ImageResources;

public class Window extends JFrame {

    private final JLayeredPane LAYERS; // Layered pane to manage multiple panels
    private String username; // Stores logged-in username
    private String password; // Stores logged-in password
    private LoginPanel loginPanel; // Panel for login form
    private DashboardPanel dashboardPanel;// Main dashboard panel
    private CrudPanel crudPanel; // Panel for CRUD operations
    private MenuPanel menuPanel; // Side menu panel
    private final Timer TIMER; // Timer to refresh dashboard data periodically

    public Window() {
        super("SICUC - Iniciar Sesión"); // Initial window title (Login)
        setLayout(null);
        setSize(510, 705);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window on screen
        setIconImages(Arrays.asList(
                ImageResources.ICON_16, ImageResources.ICON_32, ImageResources.ICON_48,
                ImageResources.ICON_64, ImageResources.ICON_128, ImageResources.ICON_256));
        setResizable(false);

        LAYERS = new JLayeredPane();
        LAYERS.setLayout(null);
        LAYERS.setBounds(0, 0, 1010, 705);
        setContentPane(LAYERS);

        loginPanel = new LoginPanel(this); // Initialize login panel
        loginPanel.setBounds(0, 0, 510, 705);
        LAYERS.add(loginPanel, JLayeredPane.DEFAULT_LAYER);

        // Timer to refresh dashboard data every 5 seconds
        TIMER = new Timer(5000, e -> {
            dashboardPanel.refreshData();
        });
    }

    public void login(String username, String password) {
        setVisible(false);
        setResizable(true);
        LAYERS.remove(loginPanel); // Remove login panel after successful login
        setTitle("SICUC - Inicio"); // Update window title
        setSize(1010, 705);
        setLocationRelativeTo(null);
        this.username = username; // Save username and password
        this.password = password;

        crudPanel = new CrudPanel(this);
        crudPanel.setBounds(0, 0, 1010, 705);
        menuPanel = new MenuPanel(this);
        menuPanel.setUsername(username);
        menuPanel.setBounds(0, 0, 1010, 705);
        menuPanel.setVisible(false);
        dashboardPanel = new DashboardPanel(this, crudPanel, menuPanel);
        dashboardPanel.setBounds(0, 0, 1010, 705);
        dashboardPanel.loadData(); // Load initial data in dashboard

        LAYERS.add(dashboardPanel, JLayeredPane.DEFAULT_LAYER);
        LAYERS.add(menuPanel, JLayeredPane.PALETTE_LAYER);
        setResizable(false);
        setVisible(true);
        renderer();
    }

    public void showMenuPanel() {
        menuPanel.setVisible(true); // Show the side menu
    }

    public void disguiseMenuPanel() {
        menuPanel.setVisible(false); // Hide the side menu
    }

    public void changeToTab(String tab) {
        LAYERS.remove(crudPanel);
        LAYERS.remove(dashboardPanel);
        if (tab.equals("home")) {
            setTitle("SICUC - Inicio");
            dashboardPanel.loadData(); // Load data if switching to home tab
            LAYERS.add(dashboardPanel);
        } else {
            stopTimer(); // Stop timer when switching away from home tab
            setTitle("SICUC - Gestión de Cultores");
            crudPanel.clearSearch();
            crudPanel.loadData("");
            LAYERS.add(crudPanel);
        }
        renderer();
    }

    public void logout() {
        TIMER.stop(); // Stop refresh timer
        setVisible(false);
        setResizable(true);
        LAYERS.removeAll(); // Clear all panels
        setTitle("SICUC - Iniciar Sesión"); // Reset title to login
        setSize(510, 705);
        setLocationRelativeTo(null);
        this.username = ""; // Clear stored credentials
        this.password = "";
        loginPanel = new LoginPanel(this); // Create new login panel
        loginPanel.setBounds(0, 0, 510, 705);
        LAYERS.add(loginPanel, JLayeredPane.DEFAULT_LAYER);
        setResizable(false);
        setVisible(true);
        renderer();
    }

    public void restartTimer() {
        TIMER.restart(); // Restart the refresh timer
    }

    public void stopTimer() {
        TIMER.stop(); // Stop the refresh timer
    }

    public void renderer() {
        LAYERS.repaint(); // Refresh UI
        LAYERS.revalidate();
    }

    public String getUsername() {
        return username; // Getter for username
    }

    public String getPassword() {
        return password; // Getter for password
    }

}
