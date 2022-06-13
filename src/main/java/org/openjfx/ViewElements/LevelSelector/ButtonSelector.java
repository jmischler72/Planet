package org.openjfx.ViewElements.LevelSelector;

import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.shape.Shape;
import org.openjfx.Models.Level.LevelType;
import org.openjfx.ViewElements.ButtonAnimation;

public class ButtonSelector extends ButtonAnimation {

    private final ColorAdjust colorAdjust = new ColorAdjust();

    public ButtonSelector(double[] position, double[]size, Shape shape, String imageName) {
        super(size);
        setShape(shape);
        setLayoutX(position[0] - getPrefWidth() / 2);
        setLayoutY(position[1] - getPrefHeight() / 2);


        createBackground(imageName);
    }

    public ButtonSelector(double[] position, double[]size, Shape shape, LevelType type) {
        super(size);
        setShape(shape);
        setLayoutX(position[0] - getPrefWidth() / 2);
        setLayoutY(position[1] - getPrefHeight() / 2);


        String imageName;
        switch (type) {
            case Boss:
                imageName = "lvl_button_boss.png";
                break;
            case Shop:
                imageName = "lvl_button_shop.png";
                break;
            case Enemy:
                imageName = "lvl_button.png";
                break;
            default:
                imageName = "";
                System.out.println("Level type not found");
        }
            
        createBackground(imageName);
    }

    private void createBackground(String imageName) {
        Image image = new Image(getClass().getResource(imageName).toExternalForm());
        BackgroundImage background = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(background));
    }
}
