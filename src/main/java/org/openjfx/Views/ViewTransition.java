package org.openjfx.Views;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.openjfx.Models.Level.Level;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ViewTransition extends View {

    View nextView;
    View lastView;

    String text;

    public ViewTransition(View lastView, View nextView, String text) {
        super(new StackPane());
        this.nextView = nextView;
        this.lastView = lastView;
        this.text = text;
    }

    public void render() {
        this.setBackgroundColor(Color.BLACK);

        Label levelName = new Label(text);
        levelName.setTextFill(Color.LIGHTYELLOW);

        try {
            levelName.setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/Frozito_font.ttf"), 64));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        addElement(levelName);

        lastView.renderView(this);

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
