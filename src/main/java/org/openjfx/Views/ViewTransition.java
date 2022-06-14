package org.openjfx.Views;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ViewTransition extends View {

    View nextView;
    View lastView;

    Label levelName;

    public ViewTransition(Pane pane, View lastView, View nextView, String text) {
        super(pane);
        this.nextView = nextView;
        this.lastView = lastView;
        this.levelName = new Label(text);
        levelName.setTextFill(Color.LIGHTYELLOW);
        this.setBackgroundColor(Color.BLACK);
    }

    public void render() {
        levelName.setLayoutX(WIDTH / 2 - levelName.getWidth() / 2);
        levelName.setLayoutY(HEIGHT / 2 - levelName.getHeight() / 2);

        try {
            levelName.setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/Frozito_font.ttf"), 100));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        addElement(levelName);

        lastView.renderView(this);
//
//        FadeTransition fadeUp = new FadeTransition();
//        fadeUp.setNode(this.getPane());
//        fadeUp.setDuration(Duration.seconds(1.5));
//        fadeUp.setFromValue(0);
//        fadeUp.setToValue(1);
//        fadeUp.play();


        delay(1000, () -> {
            renderView(nextView);
            FadeTransition fadeDown = new FadeTransition();
            fadeDown.setNode(nextView.getPane());
            fadeDown.setDuration(Duration.seconds(1.5));
            fadeDown.setFromValue(0);
            fadeDown.setToValue(1);
            fadeDown.play();

        });
    }

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

}
