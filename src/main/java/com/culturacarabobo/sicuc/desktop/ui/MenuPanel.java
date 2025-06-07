package com.culturacarabobo.sicuc.desktop.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class MenuPanel extends JPanel {

    private final HamburgerMenu HAMBURGER_MENU;

    public MenuPanel(Window window) {
        super();
        setLayout(null);
        setOpaque(false);

        HAMBURGER_MENU = new HamburgerMenu(this, window);
        HAMBURGER_MENU.setBounds(0, 0, 360, 700);
        add(HAMBURGER_MENU);

        // Click outside menu hides the panel
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getX() > 360)
                    window.disguiseMenuPanel();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(0, 0, 0, 100)); // translucent black overlay
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    public void setTabSelected(String tabSelected) {
        HAMBURGER_MENU.setTabSelected(tabSelected);
    }

    public void setUsername(String username) {
        HAMBURGER_MENU.setUsername(username);
    }

}
