package org.openjfx.ViewElements.LevelEnemy;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import org.openjfx.ViewElements.ButtonAnimation;

public class ButtonMenu extends ButtonAnimation {

    public ButtonMenu( double[]size, String text) {
        super(size);

        setText(text);
        setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
