package com.culturacarabobo.sicuc.desktop.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import com.culturacarabobo.sicuc.desktop.utils.CursorStyles;
import com.culturacarabobo.sicuc.desktop.utils.ImageResources;

public class HamburgerMenuButton extends JLabel {

    public HamburgerMenuButton(Window window) {
        setIcon(ImageResources.HAMBURGER_MENU_ICON); // Default icon

        HamburgerMenuButton self = this;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                self.setIcon(ImageResources.HAMBURGER_MENU_ICON_HOVER); // Hover icon
                self.setCursor(CursorStyles.HAND_CURSOR); // Hand cursor on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                self.setIcon(ImageResources.HAMBURGER_MENU_ICON); // Revert to default icon
                self.setCursor(CursorStyles.DEFAULT_CURSOR); // Default cursor
            }

            @Override
            public void mousePressed(MouseEvent e) {
                self.setIcon(ImageResources.HAMBURGER_MENU_ICON_PRESSED); // Pressed icon
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX(), y = e.getY();
                if (x >= 0 && x <= 48 && y >= 0 && y <= 48) {
                    self.setIcon(ImageResources.HAMBURGER_MENU_ICON_HOVER); // Hover icon after click
                    window.showMenuPanel(); // Show menu panel
                } else {
                    self.setIcon(ImageResources.HAMBURGER_MENU_ICON); // Default icon if outside bounds
                }
            }
        });
    }

}
