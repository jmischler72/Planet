package org.openjfx.ViewElements.LevelSelector;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.shape.Shape;
import org.openjfx.Models.Level.Level;
import org.openjfx.Models.Level.LevelType;
import org.openjfx.ViewElements.ButtonAnimation;

public class ButtonSelector extends ButtonAnimation {

    private Level level;

    private boolean isDone = false;

    public ButtonSelector(Level level, double[] position, double[] size, Shape shape, String imageName) {
        super(size);
        setShape(shape);
        setLayoutX(position[0] - getPrefWidth() / 2);
        setLayoutY(position[1] - getPrefHeight() / 2);
        this.level = level;

        createBackground(imageName);
    }

    public ButtonSelector(Level level, double[] size, Shape shape ) {
        super(size);
        setShape(shape);
        setLayoutX(level.getPosition()[0] - getPrefWidth() / 2);
        setLayoutY(level.getPosition()[1] - getPrefHeight() / 2);
        this.level = level;

        String imageName;
        switch (level.getType()) {
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

    public Level getLevel() {
        return level;
    }

    public void setDone() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(0.5);
        setEffect(colorAdjust);
    }

}
