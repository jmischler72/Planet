package org.openjfx.Models;

import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Properties;

public class LevelInfosSubScene extends SubScene {

    private static Properties properties;
    private static final int WIDHT = 200;
    private static final int HEIGHT = 200;

    public LevelInfosSubScene() {
        super(new AnchorPane(), WIDHT, HEIGHT);
        setLayoutX(this.getLayoutX() + 40);
        setLayoutY(this.getLayoutY() + 60);

        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource("Infos_subscene_assets/infos_subscene_background.png").toExternalForm(), WIDHT, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane infosRoot = (AnchorPane) this.getRoot();
        infosRoot.setBackground(new Background(background));

        Button b = new Button();
        b.setPrefWidth(80);
        b.setPrefHeight(30);
        b.setLayoutX(WIDHT/2 - b.getPrefWidth()/2);
        b.setLayoutY(HEIGHT - 70);

        b.setBackground(new Background(
                new BackgroundImage(
                        new Image(getClass().getResource("Infos_subscene_assets/fight_button.png").toExternalForm(), 80, 30, false, true),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null)
        ));
        ((AnchorPane) this.getRoot()).getChildren().add(b);
    }
}
