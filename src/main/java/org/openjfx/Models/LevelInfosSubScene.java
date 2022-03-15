package org.openjfx.Models;

import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Properties;

public class LevelInfosSubScene extends SubScene {

    private static Properties properties;
    private static final int WIDHT = 100;
    private static final int HEIGHT = 100;

    public LevelInfosSubScene() {
        super(new AnchorPane(), WIDHT, HEIGHT);
        setLayoutX(this.getLayoutX() + 40);
        setLayoutY(this.getLayoutY() + 60);

        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource("lvl_button_pressed.png").toExternalForm(), WIDHT, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane infosRoot = (AnchorPane) this.getRoot();
        infosRoot.setBackground(new Background(background));

        Button b = new Button("Fight !");
        ((AnchorPane) this.getRoot()).getChildren().add(b);
    }
}
