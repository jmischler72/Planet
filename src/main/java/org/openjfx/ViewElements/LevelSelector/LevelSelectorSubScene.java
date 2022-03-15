package org.openjfx.ViewElements.LevelSelector;

import javafx.animation.FadeTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.openjfx.Models.Level;

public class LevelSelectorSubScene extends SubScene {

    private boolean isHidden;

    public LevelSelectorSubScene(int[] position) {
        /*size*/
        super(new AnchorPane(), 400, 250);
        this.setFill(Color.BLUE);
        this.setOpacity(0);


        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource("green_planet.png").toExternalForm(), 400, 250, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        this.getPane().setBackground(new Background(background));


        Text text = new Text(100, 50, "text");
        text.setFill(Color.NAVAJOWHITE);

        Rectangle agent = new Rectangle(200,10);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(agent, text);
        stack.setLayoutX(200);

        this.getPane().getChildren().add(stack);

        isHidden = true;

        /*positions*/
        setLayoutX(position[0]);
        setLayoutY(position[1]);

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

