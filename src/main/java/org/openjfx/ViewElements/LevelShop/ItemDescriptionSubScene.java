package org.openjfx.ViewElements.LevelShop;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.openjfx.Models.Item;

public class ItemDescriptionSubScene extends SubScene {

    private static final int WIDTH = 240;
    private static final int HEIGHT = 250;

    public ItemDescriptionSubScene(Parent parent, Item item) {
        super(new AnchorPane(), WIDTH, HEIGHT);
        setLayoutX(350);
        setLayoutY(340);

        this.setOpacity(0);
        this.setDisable(true);

        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource("Subscene/background.png").toExternalForm(), WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane infosRoot = (AnchorPane) this.getRoot();
        infosRoot.setBackground(new Background(background));
    }

    public void moveSubScene() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(0.1));
        fade.setAutoReverse(true);
        fade.setNode(this);

        if (isDisable()) {
            fade.setFromValue(0);
            fade.setToValue(1);
            this.setDisable(false);
        } else {
            fade.setFromValue(1);
            fade.setToValue(0);
            this.setDisable(true);
        }
        fade.play();
    }

    public void addElement(Node element) {
        AnchorPane root = (AnchorPane) this.getRoot();
        root.getChildren().add(element);
    }
}
