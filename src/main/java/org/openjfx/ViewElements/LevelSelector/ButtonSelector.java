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
import org.openjfx.ViewElements.ButtonAnimation;

public class ButtonSelector extends ButtonAnimation {

    private ColorAdjust colorAdjust = new ColorAdjust();

    public ButtonSelector(double[] position, double[]size, Shape shape, String imageName) {
        super(position, size, shape);


        Image image = new Image(getClass().getResource(imageName).toExternalForm());
        BackgroundImage background = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(background));
    }



}
