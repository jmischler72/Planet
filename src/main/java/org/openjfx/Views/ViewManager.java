package org.openjfx.Views;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*
    ViewManager permet de gerer et stocker les infos concernant le stage ainsi que la scene active
 */
public class ViewManager {

    private static final int HEIGHT = 900;
    private static final int WIDTH = 1500;
    private AnchorPane mainPane;
    private View activeScene;
    private static Stage mainStage;

    public ViewManager(){
        mainPane = new AnchorPane();
        activeScene = new ViewLevelSelector(mainPane, this);
        mainStage = new Stage();
        mainStage.setScene(activeScene);
    }

    public Stage getMainStage(){
        return mainStage;
    }

    public Pane getMainPane(){ return mainPane;}

    public View getActiveScene() {
        return activeScene;
    }

    public int[] getSize(){
        return new int[]{WIDTH,HEIGHT};
    }

    public void renderScene(View scene){
        activeScene.stopAnimations();
        renderTransition(scene);
    }

    public void reloadPane(){
        this.mainPane = new AnchorPane();
    }

    private void renderTransition(View nextScene) {
        FadeTransition fadeUp = new FadeTransition();
        fadeUp.setNode(activeScene.getPane());
        fadeUp.setDuration(Duration.seconds(1.5));
        fadeUp.setFromValue(1);
        fadeUp.setToValue(0);

        View transitionScene = new View(new AnchorPane(), this);
        transitionScene.setBackground("white_back.jpg");
        Label levelName = new Label("TRANSITION");
        try {
            levelName.setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/Frozito_font.ttf"), 64));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        levelName.setPrefWidth(450);
        levelName.setPrefHeight(50);

        levelName.setLayoutX(WIDTH/2 - levelName.getPrefWidth()/2);
        levelName.setLayoutY(HEIGHT/2 - levelName.getPrefHeight()/2);
        transitionScene.addElement(levelName);

        fadeUp.setOnFinished((event) -> {
            activeScene = transitionScene;
            mainStage.setScene(transitionScene);

            delay(2000, () -> {
                activeScene = nextScene;
                mainStage.setScene(nextScene);

                FadeTransition fadeDown = new FadeTransition();
                fadeDown.setNode(activeScene.getPane());
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
