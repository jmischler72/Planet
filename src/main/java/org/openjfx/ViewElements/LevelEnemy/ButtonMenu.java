package org.openjfx.ViewElements.LevelEnemy;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import org.openjfx.ViewElements.ButtonAnimation;
import org.openjfx.Views.View;
import org.openjfx.Views.ViewLevelEnemy;
import org.openjfx.Views.ViewTransition;

public class ButtonMenu extends ButtonAnimation {

    private ColorAdjust colorAdjust = new ColorAdjust();

    public ButtonMenu(double[] position, double[]size, Shape shape, String text) {
        super(position,size,shape);

        setText(text);

        setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        setOnAction((playEvent) -> {
            System.out.println("attack");


        });
    }
}
