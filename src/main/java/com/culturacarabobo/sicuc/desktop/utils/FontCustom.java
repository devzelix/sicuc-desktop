package com.culturacarabobo.sicuc.desktop.utils;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

public class FontCustom {

    // Loads the bold title font from resources and registers it in the system
    public static Font loadFontTitle() {
        try (InputStream is = FontCustom.class.getResourceAsStream("/fonts/TitilliumWeb-Bold.ttf")) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Loads the regular text font from resources and registers it in the system
    public static Font loadFontText() {
        try (InputStream is = FontCustom.class.getResourceAsStream("/fonts/TitilliumWeb-Regular.ttf")) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
