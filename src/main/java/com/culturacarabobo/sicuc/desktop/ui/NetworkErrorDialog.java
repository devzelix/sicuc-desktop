package com.culturacarabobo.sicuc.desktop.ui;

import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.CursorStyles;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.formdev.flatlaf.FlatClientProperties;

public class NetworkErrorDialog extends JDialog {

    public NetworkErrorDialog(JFrame parent) {
        super();
        setModal(true);
        setLayout(null);
        setTitle("Connection Error"); // Dialog title
        setIconImages(Arrays.asList(ImageResources.ICON_16, ImageResources.ICON_32, ImageResources.ICON_48,
                ImageResources.ICON_64, ImageResources.ICON_128, ImageResources.ICON_256)); // Set icon images for
                                                                                            // dialog
        setSize(510, 255);
        setBackground(ColorPalette.INPUT_COLOR);
        setLocationRelativeTo(parent);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel image = new JLabel(ImageResources.NETWORK_ERROR_ICON); // Error icon label
        image.setBounds(8, 15, 128, 128);

        JLabel label = new JLabel(
                "<html><body><p style=\"text-align:center\">Could not connect to the server.</p></body></html>"); // Message
                                                                                                                  // label
        label.setFont(FontStyles.FONT_TEXT.deriveFont(30f));
        label.setForeground(ColorPalette.TERTIARY_COLOR);
        label.setBounds(175, 40, 300, 80);

        JButton closeButton = new JButton("CLOSE"); // Close button
        closeButton.setForeground(ColorPalette.TEXT_COLOR);
        closeButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
        closeButton.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        closeButton.setBackground(ColorPalette.ERROR_COLOR);
        closeButton.setBounds(158, 153, 150, 45);
        closeButton.setCursor(CursorStyles.HAND_CURSOR);
        closeButton.addActionListener(e -> {
            System.exit(0); // Exit application on close
        });

        JButton reconnectButton = new JButton("RECONNECT"); // Reconnect button
        reconnectButton.setForeground(ColorPalette.TEXT_COLOR);
        reconnectButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
        reconnectButton.putClientProperty(FlatClientProperties.STYLE, "arc: 40");
        reconnectButton.setBackground(ColorPalette.PRIMARY_COLOR);
        reconnectButton.setBounds(338, 153, 150, 45);
        reconnectButton.setCursor(CursorStyles.HAND_CURSOR);
        reconnectButton.addActionListener(e -> {
            dispose(); // Close dialog to attempt reconnect
        });

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 510, 255);
        panel.setLayout(null);
        panel.add(image);
        panel.add(label);
        panel.add(reconnectButton);
        panel.add(closeButton);

        getContentPane().add(panel);

        setVisible(true);

        reconnectButton.requestFocus();

    }
}
