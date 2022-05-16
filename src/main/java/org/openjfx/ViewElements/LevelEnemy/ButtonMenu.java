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
import org.openjfx.Views.View;
import org.openjfx.Views.ViewLevelEnemy;
import org.openjfx.Views.ViewTransition;

public class ButtonMenu extends Button {

    private ColorAdjust colorAdjust = new ColorAdjust();

    public ButtonMenu(double[] position, double[]size, Shape shape, String text) {
        super();
        setShape(shape);
        setPrefWidth(size[0]);
        setPrefHeight(size[1]);
        setLayoutX(position[0] - getPrefWidth() / 2);
        setLayoutY(position[1] - getPrefHeight() / 2);

        initButtonListeners();
        setText(text);

        setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        setOnAction((playEvent) -> {
            System.out.println("attack");


        });
    }

    private void initButtonListeners() {
        setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                colorAdjust.setBrightness(-0.5);
                setEffect(colorAdjust);
            }
        });

        setOnMouseReleased(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                colorAdjust.setBrightness(0);
                setEffect(colorAdjust);

            }
        });

        setOnMouseEntered(event -> {
            setEffect(new DropShadow());
        });

        setOnMouseExited(event -> {
            setEffect(null);
        });
    }

}
