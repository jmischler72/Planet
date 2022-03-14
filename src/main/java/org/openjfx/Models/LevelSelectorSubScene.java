package org.openjfx.Models;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class LevelSelectorSubScene extends SubScene {


//    private final static String FONT_PATH = "/resources/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = LevelSelectorSubScene.class.getResource("green_planet.png").toExternalForm();

    private boolean isHidden;


    public LevelSelectorSubScene() {
        super(new AnchorPane(), 600, 400);
        prefWidth(600);
        prefHeight(400);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 400, 200, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));

        isHidden = true;

        setLayoutX(1024);
        setLayoutY(180);

    }

    public void moveSubScene() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(0.1));
        fade.setAutoReverse(true);
        fade.setNode(this);

        if (isHidden) {
            fade.setFromValue(0);
            fade.setToValue(10);

            isHidden = false;

        } else {
            fade.setFromValue(10);
            fade.setToValue(0);

            isHidden = true;
        }
        fade.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

}

