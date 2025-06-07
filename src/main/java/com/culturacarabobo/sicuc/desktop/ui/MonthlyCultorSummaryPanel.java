package com.culturacarabobo.sicuc.desktop.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.culturacarabobo.sicuc.desktop.utils.InsetsConstants;
import com.formdev.flatlaf.ui.FlatLineBorder;

public class MonthlyCultorSummaryPanel extends JPanel {

    private final JLabel TOTAL; // Displays total new cultors
    private final JLabel LOADER; // Animated loading icon

    public MonthlyCultorSummaryPanel() {
        setLayout(null);
        setBorder(new FlatLineBorder(InsetsConstants.NO_PADDING, ColorPalette.PRIMARY_COLOR, 5, 60));
        setBackground(null);

        JLabel title = new JLabel("Nuevos Cultores del Mes"); // Title
        title.setFont(FontStyles.FONT_TITLE.deriveFont(25f));
        title.setBounds(18, 14, 272, 25);
        add(title);

        TOTAL = new JLabel(); // Displays numeric total
        TOTAL.setFont(FontStyles.FONT_TEXT.deriveFont(47f));
        TOTAL.setBounds(16, 78, 272, 40);
        TOTAL.setHorizontalAlignment(JLabel.CENTER);
        add(TOTAL);

        LOADER = new JLabel(ImageResources.LOADER_ICON_80); // Loader visible only while loading
        LOADER.setVisible(false);
        LOADER.setBounds(112, 67, 80, 80);
        add(LOADER);
    }

    public void showLoader() {
        TOTAL.setVisible(false);
        LOADER.setVisible(true);
    }

    public void disguiseLoader() {
        LOADER.setVisible(false);
        TOTAL.setVisible(true);
    }

    public String getTotal() {
        return TOTAL.getText();
    }

    public void setTotal(String total) {
        TOTAL.setText(total);
    }
}
