package com.culturacarabobo.sicuc.desktop.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.CursorStyles;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.IdToNameResolver;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.culturacarabobo.sicuc.desktop.utils.InsetsConstants;
import com.culturacarabobo.sicuc.desktop.utils.NameToIdResolver;
import com.formdev.flatlaf.ui.FlatLineBorder;

public class CaraboboMapPanel extends JPanel {

    private final DashboardPanel DASHBOARD_PANEL;
    private final JLabel TITLE;
    private final JLabel CLEAN_BUTTON;
    private final JLabel MAP;

    public CaraboboMapPanel(DashboardPanel dashboardPanel) {
        super();
        setLayout(null);
        // Set border with custom color and rounded corners
        setBorder(new FlatLineBorder(InsetsConstants.NO_PADDING, ColorPalette.PRIMARY_COLOR, 5, 60));
        setBackground(null);

        DASHBOARD_PANEL = dashboardPanel;

        // Title label showing selected municipality name
        TITLE = new JLabel(IdToNameResolver.getMunicipality(DASHBOARD_PANEL.getMunicipalityId()));
        TITLE.setFont(FontStyles.FONT_TITLE.deriveFont(27f));
        TITLE.setBounds(210, 14, 180, 33);
        TITLE.setHorizontalAlignment(JLabel.CENTER);

        // Button to clear the selection, initially hidden
        CLEAN_BUTTON = new JLabel(ImageResources.BROOM_ICON);
        CLEAN_BUTTON.setBounds(23, 280, 61, 61);
        CLEAN_BUTTON.setVisible(false);

        // Map image label
        MAP = new JLabel(ImageResources.CARABOBO_MAP);
        MAP.setBounds(15, 12, 393, 336);

        // Mouse listener for CLEAN_BUTTON to handle hover, press, release effects and
        // reset action
        CLEAN_BUTTON.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                CLEAN_BUTTON.setIcon(ImageResources.BROOM_ICON_HOVER);
                CLEAN_BUTTON.setCursor(CursorStyles.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                CLEAN_BUTTON.setIcon(ImageResources.BROOM_ICON);
                CLEAN_BUTTON.setCursor(CursorStyles.DEFAULT_CURSOR);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                CLEAN_BUTTON.setIcon(ImageResources.BROOM_ICON_PRESSED);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // If release inside button bounds, reset selection and reload data
                if (x >= 0 && x <= 61 && y >= 0 && y <= 61) {
                    resetMunicipalitySelected();
                    DASHBOARD_PANEL.loadData();
                    DASHBOARD_PANEL.restartTimer();
                    CLEAN_BUTTON.setVisible(false);
                }
                CLEAN_BUTTON.setIcon(ImageResources.BROOM_ICON);
            }
        });

        // Mouse motion listener for MAP to update icon and cursor based on mouse
        // position over municipalities
        MAP.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Icon icon = null;
                String text = "";
                String municipalitySelected = IdToNameResolver.getMunicipality(DASHBOARD_PANEL.getMunicipalityId());
                if (x >= 91 && x <= 99 && y >= 157 && y <= 168 && !municipalitySelected.equals("Bejuma")) {
                    icon = ImageResources.BEJUMA_HOVER;
                    text = "Bejuma";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 278 && x <= 286 && y >= 231 && y <= 242
                        && !municipalitySelected.equals("Carlos Arvelo")) {
                    icon = ImageResources.CARLOS_ARVELO_HOVER;
                    text = "Carlos Arvelo";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 307 && x <= 315 && y >= 134 && y <= 145
                        && !municipalitySelected.equals("Diego Ibarra")) {
                    icon = ImageResources.DIEGO_IBARRA_HOVER;
                    text = "Diego Ibarra";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 230 && x <= 238 && y >= 143 && y <= 154
                        && !municipalitySelected.equals("Guacara")) {
                    icon = ImageResources.GUACARA_HOVER;
                    text = "Guacara";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 68 && x <= 76 && y >= 52 && y <= 63
                        && !municipalitySelected.equals("Juan José Mora")) {
                    icon = ImageResources.JUAN_JOSE_MORA_HOVER;
                    text = "Juan José Mora";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 122 && x <= 130 && y >= 226 && y <= 237
                        && !municipalitySelected.equals("Libertador")) {
                    icon = ImageResources.LIBERTADOR_HOVER;
                    text = "Libertador";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 222 && x <= 230 && y >= 192 && y <= 203
                        && !municipalitySelected.equals("Los Guayos")) {
                    icon = ImageResources.LOS_GUAYOS_HOVER;
                    text = "Los Guayos";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 24 && x <= 32 && y >= 203 && y <= 214
                        && !municipalitySelected.equals("Miranda")) {
                    icon = ImageResources.MIRANDA_HOVER;
                    text = "Miranda";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 46 && x <= 54 && y >= 158 && y <= 169
                        && !municipalitySelected.equals("Montalbán")) {
                    icon = ImageResources.MONTALBAN_HOVER;
                    text = "Montalbán";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 152 && x <= 160 && y >= 131 && y <= 142
                        && !municipalitySelected.equals("Naguanagua")) {
                    icon = ImageResources.NAGUANAGUA_HOVER;
                    text = "Naguanagua";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 166 && x <= 174 && y >= 78 && y <= 89
                        && !municipalitySelected.equals("Puerto Cabello")) {
                    icon = ImageResources.PUERTO_CABELLO_HOVER;
                    text = "Puerto Cabello";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 198 && x <= 206 && y >= 134 && y <= 145
                        && !municipalitySelected.equals("San Diego")) {
                    icon = ImageResources.SAN_DIEGO_HOVER;
                    text = "San Diego";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 270 && x <= 278 && y >= 140 && y <= 151
                        && !municipalitySelected.equals("San Joaquín")) {
                    icon = ImageResources.SAN_JOAQUIN_HOVER;
                    text = "San Joaquín";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);
                } else if (x >= 176 && x <= 184 && y >= 227 && y <= 238
                        && !municipalitySelected.equals("Valencia")) {
                    icon = ImageResources.VALENCIA_HOVER;
                    text = "Valencia";
                    MAP.setCursor(CursorStyles.HAND_CURSOR);

                    // Show selected municipality icon if no hover detected
                } else {
                    if (municipalitySelected.equals("Bejuma")) {
                        icon = ImageResources.BEJUMA_SELECTED;
                    } else if (municipalitySelected.equals("Carlos Arvelo")) {
                        icon = ImageResources.CARLOS_ARVELO_SELECTED;
                    } else if (municipalitySelected.equals("Diego Ibarra")) {
                        icon = ImageResources.DIEGO_IBARRA_SELECTED;
                    } else if (municipalitySelected.equals("Guacara")) {
                        icon = ImageResources.GUACARA_SELECTED;
                    } else if (municipalitySelected.equals("Juan José Mora")) {
                        icon = ImageResources.JUAN_JOSE_MORA_SELECTED;
                    } else if (municipalitySelected.equals("Libertador")) {
                        icon = ImageResources.LIBERTADOR_SELECTED;
                    } else if (municipalitySelected.equals("Los Guayos")) {
                        icon = ImageResources.LOS_GUAYOS_SELECTED;
                    } else if (municipalitySelected.equals("Miranda")) {
                        icon = ImageResources.MIRANDA_SELECTED;
                    } else if (municipalitySelected.equals("Montalbán")) {
                        icon = ImageResources.MONTALBAN_SELECTED;
                    } else if (municipalitySelected.equals("Naguanagua")) {
                        icon = ImageResources.NAGUANAGUA_SELECTED;
                    } else if (municipalitySelected.equals("Puerto Cabello")) {
                        icon = ImageResources.PUERTO_CABELLO_SELECTED;
                    } else if (municipalitySelected.equals("San Diego")) {
                        icon = ImageResources.SAN_DIEGO_SELECTED;
                    } else if (municipalitySelected.equals("San Joaquín")) {
                        icon = ImageResources.SAN_JOAQUIN_SELECTED;
                    } else if (municipalitySelected.equals("Valencia")) {
                        icon = ImageResources.VALENCIA_SELECTED;
                    } else {
                        icon = ImageResources.CARABOBO_MAP;
                    }
                    text = municipalitySelected;
                    MAP.setCursor(CursorStyles.DEFAULT_CURSOR);
                }

                // Update map icon and title text accordingly
                MAP.setIcon(icon);
                TITLE.setText(text);
            }
        });

        // Change icon to pressed state if cursor is over a different municipality
        MAP.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Icon icon = MAP.getIcon();
                String municipalitySelected = IdToNameResolver.getMunicipality(DASHBOARD_PANEL.getMunicipalityId());

                // On release, set the municipality as selected and update the icon accordingly
                if (x >= 91 && x <= 99 && y >= 157 && y <= 168 && !municipalitySelected.equals("Bejuma")) {
                    icon = ImageResources.BEJUMA_PRESSED;
                } else if (x >= 278 && x <= 286 && y >= 231 && y <= 242
                        && !municipalitySelected.equals("Carlos Arvelo")) {
                    icon = ImageResources.CARLOS_ARVELO_PRESSED;
                } else if (x >= 307 && x <= 315 && y >= 134 && y <= 145
                        && !municipalitySelected.equals("Diego Ibarra")) {
                    icon = ImageResources.DIEGO_IBARRA_PRESSED;
                } else if (x >= 230 && x <= 238 && y >= 143 && y <= 154
                        && !municipalitySelected.equals("Guacara")) {
                    icon = ImageResources.GUACARA_PRESSED;
                } else if (x >= 68 && x <= 76 && y >= 52 && y <= 63
                        && !municipalitySelected.equals("Juan José Mora")) {
                    icon = ImageResources.JUAN_JOSE_MORA_PRESSED;
                } else if (x >= 122 && x <= 130 && y >= 226 && y <= 237
                        && !municipalitySelected.equals("Libertador")) {
                    icon = ImageResources.LIBERTADOR_PRESSED;
                } else if (x >= 222 && x <= 230 && y >= 192 && y <= 203
                        && !municipalitySelected.equals("Los Guayos")) {
                    icon = ImageResources.LOS_GUAYOS_PRESSED;
                } else if (x >= 24 && x <= 32 && y >= 203 && y <= 214
                        && !municipalitySelected.equals("Miranda")) {
                    icon = ImageResources.MIRANDA_PRESSED;
                } else if (x >= 46 && x <= 54 && y >= 158 && y <= 169
                        && !municipalitySelected.equals("Montalbán")) {
                    icon = ImageResources.MONTALBAN_PRESSED;
                } else if (x >= 152 && x <= 160 && y >= 131 && y <= 142
                        && !municipalitySelected.equals("Naguanagua")) {
                    icon = ImageResources.NAGUANAGUA_PRESSED;
                } else if (x >= 166 && x <= 174 && y >= 78 && y <= 89
                        && !municipalitySelected.equals("Puerto Cabello")) {
                    icon = ImageResources.PUERTO_CABELLO_PRESSED;
                } else if (x >= 198 && x <= 206 && y >= 134 && y <= 145
                        && !municipalitySelected.equals("San Diego")) {
                    icon = ImageResources.SAN_DIEGO_PRESSED;
                } else if (x >= 270 && x <= 278 && y >= 140 && y <= 151
                        && !municipalitySelected.equals("San Joaquín")) {
                    icon = ImageResources.SAN_JOAQUIN_PRESSED;
                } else if (x >= 176 && x <= 184 && y >= 227 && y <= 238
                        && !municipalitySelected.equals("Valencia")) {
                    icon = ImageResources.VALENCIA_PRESSED;
                }
                MAP.setIcon(icon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Icon icon = MAP.getIcon();
                if (icon == ImageResources.BEJUMA_PRESSED) {
                    icon = ImageResources.BEJUMA_SELECTED;
                    setMunicipalityId("Bejuma");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.CARLOS_ARVELO_PRESSED) {
                    icon = ImageResources.CARLOS_ARVELO_SELECTED;
                    setMunicipalityId("Carlos Arvelo");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.DIEGO_IBARRA_PRESSED) {
                    icon = ImageResources.DIEGO_IBARRA_SELECTED;
                    setMunicipalityId("Diego Ibarra");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.GUACARA_PRESSED) {
                    icon = ImageResources.GUACARA_SELECTED;
                    setMunicipalityId("Guacara");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.JUAN_JOSE_MORA_PRESSED) {
                    icon = ImageResources.JUAN_JOSE_MORA_SELECTED;
                    setMunicipalityId("Juan José Mora");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.LIBERTADOR_PRESSED) {
                    icon = ImageResources.LIBERTADOR_SELECTED;
                    setMunicipalityId("Libertador");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.LOS_GUAYOS_PRESSED) {
                    icon = ImageResources.LOS_GUAYOS_SELECTED;
                    setMunicipalityId("Los Guayos");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.MIRANDA_PRESSED) {
                    icon = ImageResources.MIRANDA_SELECTED;
                    setMunicipalityId("Miranda");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.MONTALBAN_PRESSED) {
                    icon = ImageResources.MONTALBAN_SELECTED;
                    setMunicipalityId("Montalbán");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.NAGUANAGUA_PRESSED) {
                    icon = ImageResources.NAGUANAGUA_SELECTED;
                    setMunicipalityId("Naguanagua");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.PUERTO_CABELLO_PRESSED) {
                    icon = ImageResources.PUERTO_CABELLO_SELECTED;
                    setMunicipalityId("Puerto Cabello");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.SAN_DIEGO_PRESSED) {
                    icon = ImageResources.SAN_DIEGO_SELECTED;
                    setMunicipalityId("San Diego");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.SAN_JOAQUIN_PRESSED) {
                    icon = ImageResources.SAN_JOAQUIN_SELECTED;
                    setMunicipalityId("San Joaquín");
                    CLEAN_BUTTON.setVisible(true);
                } else if (icon == ImageResources.VALENCIA_PRESSED) {
                    icon = ImageResources.VALENCIA_SELECTED;
                    setMunicipalityId("Valencia");
                    CLEAN_BUTTON.setVisible(true);
                }
                MAP.setIcon(icon);

                // Update title label to reflect the selected municipality name
                TITLE.setText(IdToNameResolver.getMunicipality(DASHBOARD_PANEL.getMunicipalityId()));
            }

        });

        add(TITLE);
        add(CLEAN_BUTTON);
        add(MAP);
    }

    // Resets the selected municipality and UI elements to default state
    public void resetMunicipalitySelected() {
        DASHBOARD_PANEL.setMunicipalityId(0);
        TITLE.setText(IdToNameResolver.getMunicipality(DASHBOARD_PANEL.getMunicipalityId()));
        CLEAN_BUTTON.setIcon(ImageResources.BROOM_ICON);
        CLEAN_BUTTON.setVisible(false);
        MAP.setIcon(ImageResources.CARABOBO_MAP);
    }

    // Sets the municipality ID, reloads data, restarts timer, and shows the clean
    // button
    private void setMunicipalityId(String municipality) {
        DASHBOARD_PANEL.setMunicipalityId(NameToIdResolver.getMunicipality(municipality));
        DASHBOARD_PANEL.loadData();
        DASHBOARD_PANEL.restartTimer();
        CLEAN_BUTTON.setVisible(true);
    }
}
