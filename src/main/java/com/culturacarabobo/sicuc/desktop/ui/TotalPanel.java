package com.culturacarabobo.sicuc.desktop.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.culturacarabobo.sicuc.desktop.utils.InsetsConstants;
import com.formdev.flatlaf.ui.FlatLineBorder;

public class TotalPanel extends JPanel {

    private final JLabel TOTAL; // Label to display the total count
    private final JLabel LOADER; // Loading icon label

    public TotalPanel() {
        super();
        setLayout(null);
        setBorder(new FlatLineBorder(InsetsConstants.NO_PADDING, ColorPalette.PRIMARY_COLOR, 5, 60));
        setBackground(null);

        JLabel title = new JLabel("Total de Cultores"); // Panel title
        title.setFont(FontStyles.FONT_TITLE.deriveFont(28f));
        title.setBounds(22, 14, 210, 25);
        add(title);

        TOTAL = new JLabel(); // Label to show the total number
        TOTAL.setFont(FontStyles.FONT_TEXT.deriveFont(47f));
        TOTAL.setBounds(20, 78, 210, 40);
        TOTAL.setHorizontalAlignment(JLabel.CENTER);
        add(TOTAL);

        LOADER = new JLabel(ImageResources.LOADER_ICON_80); // Loading icon, hidden by default
        LOADER.setVisible(false);
        LOADER.setBounds(84, 67, 80, 80);
        add(LOADER);
    }

    public void showLoader() {
        TOTAL.setVisible(false); // Hide total label
        LOADER.setVisible(true); // Show loading icon
    }

    public void disguiseLoader() {
        LOADER.setVisible(false); // Hide loading icon
        TOTAL.setVisible(true); // Show total label
    }

    public String getTotal() {
        return TOTAL.getText(); // Get the current total text
    }

    public void setTotal(String total) {
        TOTAL.setText(total); // Set the total text
    }

}
