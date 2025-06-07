package com.culturacarabobo.sicuc.desktop.ui;

import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.culturacarabobo.sicuc.desktop.services.AuthService;
import com.culturacarabobo.sicuc.desktop.utils.ColorPalette;
import com.culturacarabobo.sicuc.desktop.utils.CursorStyles;
import com.culturacarabobo.sicuc.desktop.utils.FontStyles;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;
import com.formdev.flatlaf.FlatClientProperties;

public class LoginPanel extends JPanel {

    public LoginPanel(Window window) {
        super();
        setBackground(ColorPalette.PRIMARY_COLOR);
        setLayout(null);

        JLabel userIcon = new JLabel(ImageResources.USER_ICON);
        userIcon.setBounds(175, 45, 150, 150);
        add(userIcon);

        JLabel usernameLabel = new JLabel("Nombre de Usuario :");
        usernameLabel.setForeground(ColorPalette.TEXT_COLOR);
        usernameLabel.setFont(FontStyles.FONT_TITLE.deriveFont(25f));
        usernameLabel.setBounds(65, 250, 225, 30);
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBackground(ColorPalette.INPUT_COLOR);
        usernameField.setForeground(ColorPalette.TERTIARY_COLOR);
        usernameField.setFont(FontStyles.FONT_TEXT.deriveFont(17f));
        usernameField.setBounds(65, 295, 370, 50);
        usernameField.putClientProperty(FlatClientProperties.STYLE, "arc: 15");
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                usernameField.selectAll(); // Select all on focus
            }
        });
        add(usernameField);

        JLabel passwordLabel = new JLabel("Contraseña :");
        passwordLabel.setForeground(ColorPalette.TEXT_COLOR);
        passwordLabel.setFont(FontStyles.FONT_TITLE.deriveFont(25f));
        passwordLabel.setBounds(65, 395, 150, 30);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordField.setBackground(ColorPalette.INPUT_COLOR);
        passwordField.setForeground(ColorPalette.TERTIARY_COLOR);
        passwordField.setFont(FontStyles.FONT_TEXT.deriveFont(17f));
        passwordField.setBounds(65, 440, 370, 50);
        passwordField.putClientProperty(FlatClientProperties.STYLE, "arc: 15");
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.selectAll(); // Select all on focus
            }
        });
        add(passwordField);

        JCheckBox showPassword = new JCheckBox("Mostrar contraseña");
        showPassword.setBounds(65, 500, 160, 20);
        showPassword.setForeground(ColorPalette.TEXT_COLOR);
        showPassword.setFont(FontStyles.FONT_TEXT.deriveFont(15f));
        add(showPassword);

        JButton loginButton = new JButton("INGRESAR");
        loginButton.setBackground(ColorPalette.SECONDARY_COLOR);
        loginButton.setForeground(ColorPalette.TEXT_COLOR);
        loginButton.setFont(FontStyles.FONT_TITLE.deriveFont(22f));
        loginButton.setBounds(65, 570, 370, 55);
        loginButton.putClientProperty(FlatClientProperties.STYLE, "arc: 15");
        add(loginButton);

        // Move focus from username to password on Enter
        usernameField.addActionListener(e -> passwordField.requestFocus());

        // Press login on Enter in password field
        passwordField.addActionListener(e -> loginButton.doClick());

        // Toggle show password with Enter key when checkbox focused
        showPassword.getInputMap(JCheckBox.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "toggle");
        showPassword.getActionMap().put("toggle", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPassword.setSelected(!showPassword.isSelected());
                passwordField.setEchoChar(showPassword.isSelected() ? (char) 0 : '*');
            }
        });

        // Show/hide password on checkbox toggle
        showPassword.addActionListener(e -> passwordField.setEchoChar(showPassword.isSelected() ? (char) 0 : '*'));

        // Login button action: validate and authenticate
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (username.isBlank() || password.isBlank()) {
                new GeneralDialog(window, "Campos Incompletos", ImageResources.ERROR_ICON,
                        "El usuario y la contraseña no pueden estar vacíos.", 20f, ColorPalette.ERROR_COLOR,
                        "REINTENTAR").setVisible(true);

                if (username.isBlank()) {
                    usernameField.requestFocus();
                } else {
                    passwordField.requestFocus();
                }
                return;
            }

            int responseCode = 0;
            do {
                responseCode = AuthService.login(username, password);

                if (responseCode == 200) {
                    window.login(username, password);
                } else if (responseCode == 401) {
                    new GeneralDialog(window, "Error de Autenticación", ImageResources.AUTH_ERROR_ICON,
                            "Usuario o contraseña incorrectos.", 23f, ColorPalette.ERROR_COLOR, "REINTENTAR")
                            .setVisible(true);
                    usernameField.requestFocus();
                } else {
                    new NetworkErrorDialog(window);
                }
            } while (responseCode != 200 && responseCode != 401);
        });

        // Change cursor on login button hover
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setCursor(CursorStyles.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setCursor(CursorStyles.DEFAULT_CURSOR);
            }
        });
    }

}
