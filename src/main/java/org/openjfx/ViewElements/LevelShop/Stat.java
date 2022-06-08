package org.openjfx.ViewElements.LevelShop;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Stat extends Text {
    public Stat(String s, Font font, Color color) {
        super(s);
        setFont(font);
        setFill(color);
    }
}
