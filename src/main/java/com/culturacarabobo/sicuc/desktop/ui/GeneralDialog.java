package com.culturacarabobo.sicuc.desktop.ui;

import java.awt.Color;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.formdev.flatlaf.FlatClientProperties;

public class GeneralDialog extends JDialog {
        public GeneralDialog(JFrame parent, String title, ImageIcon icon, String message, float fontSizeMessage,
                        Color colorButton, String messageButton) {
                super();
                setModal(true); // Make dialog modal, blocking input to other windows until closed
                setTitle(title); // Set dialog title
                // Set multiple icon sizes for the window (taskbar, titlebar, etc.)
                setIconImages(Arrays.asList(ImageResources.ICON_16, ImageResources.ICON_32, ImageResources.ICON_48,
                                ImageResources.ICON_64, ImageResources.ICON_128, ImageResources.ICON_256));
                setSize(405, 210); // Set fixed dialog size
                setBackground(ColorPalette.INPUT_COLOR); // Set background color of dialog
                setLocationRelativeTo(parent); // Center dialog relative to parent window

                // Label to display the icon on the left side
                JLabel image = new JLabel(icon);
                image.setBounds(20, 14, 128, 128);
                add(image);

                // Label for the main message text, formatted as centered HTML
                JLabel label = new JLabel(
                                "<html><body><p style=\"text-align:center\">" + message + "</p></body></html>");
                label.setFont(FontStyles.FONT_TEXT.deriveFont(fontSizeMessage)); // Set font with given size
                label.setForeground(ColorPalette.TERTIARY_COLOR); // Set text color
                label.setBounds(160, 25, 220, 65);
                add(label);

                // Close button with customizable text and color
                JButton closeButton = new JButton(messageButton);
                closeButton.setForeground(ColorPalette.TEXT_COLOR);
                closeButton.setFont(FontStyles.FONT_TITLE.deriveFont(16f));
                closeButton.putClientProperty(FlatClientProperties.STYLE, "arc: 40"); // Rounded corners
                closeButton.setBackground(colorButton);
                closeButton.setBounds(195, 110, 150, 45);
                // Close the dialog when button is clicked
                closeButton.addActionListener(e -> dispose());

                // Create a panel with null layout and add message label and close button
                JPanel panel = new JPanel();
                panel.setLayout(null);
                panel.add(label);
                panel.add(closeButton);

                add(panel); // Add panel to the dialog
        }
}
