package org.openjfx.Views;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ViewTransition extends View {

    View nextView;
    Pane activePane;

    public ViewTransition(Pane pane, ViewManager viewManager, View nextView, Pane activePane) {
        super(pane, viewManager);
        this.nextView = nextView;
        this.activePane = activePane;
        render();
    }

    private void render(){

        FadeTransition fadeUp = new FadeTransition();
        fadeUp.setNode(activePane);
        fadeUp.setDuration(Duration.seconds(1.5));
        fadeUp.setFromValue(1);
        fadeUp.setToValue(0);

        View transitionScene = this;
        transitionScene.setBackground("white_back.jpg");
        Label levelName = new Label("TRANSITION");
        try {
            levelName.setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/Frozito_font.ttf"), 64));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        levelName.setPrefWidth(450);
        levelName.setPrefHeight(50);

        levelName.setLayoutX(viewManager.getSize()[0]/2 - levelName.getPrefWidth()/2);
        levelName.setLayoutY(viewManager.getSize()[1]/2 - levelName.getPrefHeight()/2);
        transitionScene.addElement(levelName);

        fadeUp.setOnFinished((event) -> {
            viewManager.renderView(transitionScene);


            delay(2000, () -> {
                viewManager.renderView(nextView);

                FadeTransition fadeDown = new FadeTransition();
                fadeDown.setNode(viewManager.getActivePane());
                fadeDown.setDuration(Duration.seconds(1.5));
                fadeDown.setFromValue(0);
                fadeDown.setToValue(1);
                fadeDown.play();
            });
        });

        fadeUp.play();
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
