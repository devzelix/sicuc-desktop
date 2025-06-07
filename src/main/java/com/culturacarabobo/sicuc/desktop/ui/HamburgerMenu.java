package com.culturacarabobo.sicuc.desktop.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.CursorStyles;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;

public class HamburgerMenu extends JPanel {

    private final Window WINDOW; // Reference to main application window
    private final JLabel LETTER_USERNAME; // Label showing first letter of username (avatar)
    private final JLabel USERNAME; // Label showing full username
    private String tabSelected; // Currently selected tab identifier
    private final JButton HOME_TAB_SELECT; // Button for "Home" tab
    private final JButton MANAGEMENT_TAB_SELECT; // Button for "Management" tab

    public HamburgerMenu(MenuPanel menuPanel, Window window) {
        super();
        setLayout(null); // Absolute positioning
        setBackground(ColorPalette.PRIMARY_COLOR); // Background color of the menu panel

        WINDOW = window;

        // Circle with initial letter of username, centered in a 50x50 area
        LETTER_USERNAME = new JLabel();
        LETTER_USERNAME.setBounds(155, 72, 50, 50);
        LETTER_USERNAME.setFont(FontStyles.FONT_TITLE.deriveFont(45f));
        LETTER_USERNAME.setForeground(ColorPalette.INPUT_COLOR);
        LETTER_USERNAME.setHorizontalAlignment(JLabel.CENTER);
        add(LETTER_USERNAME);

        // Label to display full username below the avatar letter
        USERNAME = new JLabel();
        USERNAME.setBounds(62, 151, 236, 28);
        USERNAME.setFont(FontStyles.FONT_TEXT.deriveFont(18f));
        USERNAME.setForeground(ColorPalette.INPUT_COLOR);
        USERNAME.setHorizontalAlignment(JLabel.CENTER);
        add(USERNAME);

        // Close button (hamburger menu icon) at top-right corner to hide menu
        JLabel closeButton = new JLabel(ImageResources.HAMBURGER_MENU_CLOSE_ICON);
        closeButton.setBounds(305, 25, 40, 40);
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setCursor(CursorStyles.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setCursor(CursorStyles.DEFAULT_CURSOR);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                WINDOW.disguiseMenuPanel(); // Hide the menu panel
                WINDOW.renderer(); // Refresh UI after hiding
            }
        });
        add(closeButton);

        tabSelected = "home"; // Default selected tab on initialization

        // Button to select "Home" tab with icon and styling
        HOME_TAB_SELECT = new JButton("Inicio");
        HOME_TAB_SELECT.setIcon(ImageResources.HOME_ICON);
        HOME_TAB_SELECT.setIconTextGap(15);
        HOME_TAB_SELECT.setBackground(ColorPalette.PIE_COLOR_2); // Highlighted background (selected)
        HOME_TAB_SELECT.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        HOME_TAB_SELECT.setFont(FontStyles.FONT_TEXT.deriveFont(30f));
        HOME_TAB_SELECT.setForeground(ColorPalette.INPUT_COLOR);
        HOME_TAB_SELECT.setHorizontalAlignment(JButton.LEFT);
        HOME_TAB_SELECT.setBounds(0, 219, 360, 57);
        HOME_TAB_SELECT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                HOME_TAB_SELECT.setCursor(CursorStyles.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                HOME_TAB_SELECT.setCursor(CursorStyles.DEFAULT_CURSOR);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!tabSelected.equals("home"))
                    setTabSelected("home"); // Change tab only if not already selected
            }
        });
        add(HOME_TAB_SELECT);

        // Button to select "Management" tab with icon and styling
        MANAGEMENT_TAB_SELECT = new JButton("Gestión de Cultores");
        MANAGEMENT_TAB_SELECT.setIcon(ImageResources.MANAGEMENT_ICON);
        MANAGEMENT_TAB_SELECT.setIconTextGap(15);
        MANAGEMENT_TAB_SELECT.setBackground(ColorPalette.PRIMARY_COLOR); // Default background (not selected)
        MANAGEMENT_TAB_SELECT.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        MANAGEMENT_TAB_SELECT.setFont(FontStyles.FONT_TEXT.deriveFont(30f));
        MANAGEMENT_TAB_SELECT.setForeground(ColorPalette.INPUT_COLOR);
        MANAGEMENT_TAB_SELECT.setHorizontalAlignment(JButton.LEFT);
        MANAGEMENT_TAB_SELECT.setBounds(0, 276, 360, 57);
        MANAGEMENT_TAB_SELECT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                MANAGEMENT_TAB_SELECT.setCursor(CursorStyles.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MANAGEMENT_TAB_SELECT.setCursor(CursorStyles.DEFAULT_CURSOR);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!tabSelected.equals("management"))
                    setTabSelected("management"); // Change tab only if not already selected
            }
        });
        add(MANAGEMENT_TAB_SELECT);

        // Logout button with icon and styling, positioned at bottom
        JButton logout = new JButton("Cerrar Sesión");
        logout.setIcon(ImageResources.LOGOUT_ICON);
        logout.setIconTextGap(15);
        logout.setBackground(ColorPalette.PRIMARY_COLOR);
        logout.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        logout.setFont(FontStyles.FONT_TEXT.deriveFont(30f));
        logout.setForeground(ColorPalette.INPUT_COLOR);
        logout.setBounds(0, 610, 360, 57);
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logout.setCursor(CursorStyles.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logout.setCursor(CursorStyles.DEFAULT_CURSOR);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                WINDOW.logout(); // Trigger logout process
            }
        });
        add(logout);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Enable anti-aliasing for smooth edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw a filled oval (circle) behind the initial letter avatar
        g2d.setColor(ColorPalette.SECONDARY_COLOR);
        g2d.fillOval(132, 50, 96, 96);
    }

    /**
     * Updates the UI to reflect the selected tab, changing background colors
     * accordingly,
     * and instructs the main window to switch to the selected tab's content.
     * 
     * @param tabSelected "home" or "management"
     */
    public void setTabSelected(String tabSelected) {
        if (tabSelected.equals("home")) {
            HOME_TAB_SELECT.setBackground(ColorPalette.PIE_COLOR_2); // Highlight home tab
            MANAGEMENT_TAB_SELECT.setBackground(ColorPalette.PRIMARY_COLOR); // Reset management tab background
        } else if (tabSelected.equals("management")) {
            HOME_TAB_SELECT.setBackground(ColorPalette.PRIMARY_COLOR); // Reset home tab background
            MANAGEMENT_TAB_SELECT.setBackground(ColorPalette.PIE_COLOR_2); // Highlight management tab
        }
        this.tabSelected = tabSelected;
        WINDOW.changeToTab(tabSelected); // Notify main window to switch views
    }

    /**
     * Sets the username displayed on the hamburger menu.
     * Displays the first letter as uppercase in the avatar,
     * and the full username in lowercase.
     * 
     * @param username The user's name
     */
    public void setUsername(String username) {
        LETTER_USERNAME.setText(String.valueOf(username.charAt(0)).toUpperCase());
        USERNAME.setText(username.toLowerCase());
    }

}
