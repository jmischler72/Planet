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

        this.setFill(Color.BLUE);
        this.setOpacity(0);
        this.setDisable(true);


//        BackgroundImage background = new BackgroundImage(
//                new Image(getClass().getResource("green_planet.png").toExternalForm(), 400, 250, false, true),
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
//        this.getPane().setBackground(new Background(background));


        Text text = new Text(100, 50, level.getName());
        text.setFill(Color.NAVAJOWHITE);

        Rectangle cadre = new Rectangle(200,20);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(cadre, text);

        Button play = new Button("Play");
        System.out.println(play.getWidth());
        play.setLayoutY(HEIGHT-35);

        addElement(stack);
        addElement(play);


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

