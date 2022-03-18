package org.openjfx.ViewElements.LevelSelector;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.openjfx.Models.Level;

public class LevelSelectorSubScene extends SubScene {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;


    public LevelSelectorSubScene(int buttonSize, Level level) {
        /*size*/
        super(new AnchorPane(), WIDTH, HEIGHT);

        /*positions*/
        setLayoutX(level.getPosition()[0]);
        setLayoutY(level.getPosition()[1]);

        this.setOpacity(0);
        this.setDisable(true);

        Text text = new Text(100, 50, level.getName());
        text.setFill(Color.WHITE);
        text.setFont(new Font(15));

        Rectangle cadre = new Rectangle(200,20);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(cadre, text);
        System.out.println(cadre.getWidth());
        stack.setLayoutX(WIDTH/2-cadre.getWidth()/2);
        stack.setLayoutY(23);

        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource("Infos_subscene_assets/infos_subscene_background.png").toExternalForm(), WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane infosRoot = (AnchorPane) this.getRoot();
        infosRoot.setBackground(new Background(background));

        Button b = new Button();
        b.setPrefWidth(80);
        b.setPrefHeight(30);
        b.setLayoutX(WIDTH/2 - b.getPrefWidth()/2);
        b.setLayoutY(HEIGHT - 60);

        double[] position = new double[]{WIDTH/2 , HEIGHT - 60};

        ButtonAnimation b2 = new ButtonAnimation(position, new double[]{80,30}, null, "Infos_subscene_assets/fight_button.png");

        b.setOnAction((event) -> {
            System.out.println("f");

        });

        addElement(stack);
        addElement(b2);
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

