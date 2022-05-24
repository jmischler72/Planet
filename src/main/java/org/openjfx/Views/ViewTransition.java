package org.openjfx.Views;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.openjfx.Models.Level.Level;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ViewTransition extends View {

    View nextView;
    String text;

    public ViewTransition(Pane pane, ViewManager viewManager, View nextView, String text) {
        super(pane, viewManager);
        this.nextView = nextView;
        this.text = text;
        render();
    }

    private void render() {
        this.setBackgroundColor(Color.BLACK);

        Label levelName = new Label(text);
        levelName.setTextFill(Color.LIGHTYELLOW);

        try {
            levelName.setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/Frozito_font.ttf"), 64));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        addElement(levelName);



        viewManager.renderView(this);


        delay(2000, () -> {
            viewManager.renderView(nextView);

            FadeTransition fadeDown = new FadeTransition();
            fadeDown.setNode(viewManager.getActivePane());
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
