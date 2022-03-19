package org.openjfx.ViewElements.LevelSelector;

import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.shape.Shape;

public class ButtonAnimation extends Button {

    private ColorAdjust colorAdjust = new ColorAdjust();

    public ButtonAnimation(double[] position, double[]size, Shape shape, String imageName) {
        super();
        setShape(shape);
        setPrefWidth(size[0]);
        setPrefHeight(size[1]);
        setLayoutX(position[0] - getPrefWidth() / 2);
        setLayoutY(position[1] - getPrefHeight() / 2);

        initButtonListeners();

        Image image = new Image(getClass().getResource(imageName).toExternalForm());
        BackgroundImage background = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(background));
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