package org.openjfx.Views;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javafx.util.Duration;
import org.openjfx.Controllers.ControllerLevelSelector;
import org.openjfx.Models.Level;
import org.openjfx.ViewElements.LevelSelector.LevelButton;
import org.openjfx.ViewElements.LevelSelector.LevelSelectorSubScene;
import org.openjfx.Models.Planet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import static javafx.util.Duration.millis;


public class ViewLevelSelector extends Scene {
    private Pane pane;
    private Planet planet;

    private ArrayList<LevelButton> buttonList  = new ArrayList<LevelButton>();

    public ViewLevelSelector(Pane pane, int width, int height, ViewManager viewManager) {
        super(pane, height, width);
        this.pane = pane;

        setBackground();
        planet = new Planet();

        Circle circlePlanet = rendercirclePlanet(planet);
        Circle shadow = renderCircleShadow(circlePlanet, 80, new int[]{0, 10});
//        renderShadowAnimation(circlePlanet,shadow);
        this.addElement(shadow);
        this.addElement(circlePlanet);

        ArrayList<int[]> positions = new ArrayList<int[]>();

        for (Level level : planet.getLevels()) {

            Circle circleMarkers = new Circle(circlePlanet.getCenterX(), circlePlanet.getCenterY(), circlePlanet.getRadius() - 80);
            int[] position = getRandomPositionInCircle(circleMarkers);

            boolean close = true;
            do {
                close = false;
                for (int[] pos : positions) {
                    if (arePositionsClose(pos, position, 40)) {
                        position = getRandomPositionInCircle(circleMarkers);
                        close = true;
                    }
                }
            } while (close);

            level.setPosition(position);
            positions.add(position);

            Group levelGroup = new Group();

            LevelButton levelButton = new LevelButton(level.getPosition());
            LevelSelectorSubScene levelSelectorSubScene = new LevelSelectorSubScene((int) levelButton.getPrefWidth(), level);
            buttonList.add(levelButton);
            levelButton.setOnAction((event) -> {    // lambda expression
                if (levelSelectorSubScene.isDisable()) {
                    animationButtons(levelButton, false);
                } else {
                    animationButtons(levelButton, true);
                }
                levelSelectorSubScene.moveSubScene();
            });

            addElement(levelSelectorSubScene);
            addElement(levelButton);


        }
    }

    private void animationButtons(LevelButton activeButton, boolean opened) {

        for (LevelButton button : buttonList) {
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.seconds(0.1));
            fade.setAutoReverse(true);
            if (button != activeButton) {
                if(!opened){
                    fade.setFromValue(1);
                    fade.setToValue(0);
                    button.setDisable(true);
                }else{
                    fade.setFromValue(0);
                    fade.setToValue(1);
                    button.setDisable(false);
                }
                fade.setNode(button);
                fade.play();
            }
        }

    }

    private void showAllButtonsExcep() {
        for (LevelButton button : buttonList) {
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.seconds(0.1));
            fade.setAutoReverse(true);
            fade.setFromValue(0);
            fade.setToValue(1);
            button.setDisable(false);
            fade.setNode(button);
            fade.play();
        }
    }

    private void setBackground() {
        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource("space.jpg").toExternalForm(), pane.getWidth(), pane.getHeight(), false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        pane.setBackground(new Background(background));
    }

    private boolean arePositionsClose(int[] position1, int[] position2, int radius) {
        if (Math.abs(position1[0] - position2[0]) < radius) {
            System.out.println(Math.abs(position1[0]) - Math.abs(position2[0]));
            return true;
        }
        if (Math.abs(position1[1] - position2[1]) < radius) {
            return true;
        }

        return false;

    }

    private void renderShadowAnimation(Circle planet, Circle shadow) {
        final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler() {
            int movingStep = 0;

            @Override
            public void handle(Event event) {
                movingStep++;
                System.out.println(movingStep);

                double angleAlpha = movingStep * (Math.PI / 30);

                // p(x) = x(0) + r * sin(a)
                // p(y) = y(y) - r * cos(a)

                TranslateTransition move = new TranslateTransition();
                move.setNode(shadow);
                move.setToX(10 * Math.sin(angleAlpha));
                move.setToY(10 * Math.cos(angleAlpha));
                move.setDuration(millis(500));
                move.playFromStart();

                // Reset after one orbit.
                if (movingStep == 60) {
                    movingStep = 0;
                }
            }
        }), new KeyFrame(millis(500)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }


    private int[] getRandomPositionInCircle(Circle circle) {
        Random rand = new Random();
        int[] position = new int[2];

        int randomX = (int) (rand.nextInt((int) (circle.getRadius() * 2)) - circle.getRadius());
        position[0] = (int) (circle.getCenterX() + randomX);

        double cosX = (randomX / (circle.getRadius()));
        double boundY = Math.sin(Math.acos(cosX)) * circle.getRadius();

        double randomY = (rand.nextInt((int) (boundY) * 2) - boundY);
        position[1] = (int) (circle.getCenterY() + randomY);

        return position;
    }

    private Circle rendercirclePlanet(Planet planet) {
        Circle circle = new Circle();
        circle.setCenterX(pane.getWidth() / 2);
        circle.setCenterY(pane.getHeight() / 2);
        circle.setRadius(pane.getHeight() * 0.40);
        try {
            circle.setFill(new ImagePattern(
                    new Image(new FileInputStream(planet.getPlanet_file())), 0, 0, 1, 1, true

            ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return circle;
    }

    private Circle renderCircleShadow(Circle circle_to_shadow, int amount, int[] offset) {
        Shadow shadow = new Shadow(BlurType.GAUSSIAN, Color.WHITE, 10);
        shadow.setHeight(amount);
        shadow.setWidth(amount);
        Circle circle = new Circle();
        circle.setCenterX(circle_to_shadow.getCenterX() + offset[0]);
        circle.setCenterY(circle_to_shadow.getCenterY() + offset[1]);
        circle.setRadius(circle_to_shadow.getRadius());
        circle.setEffect(shadow);
        return circle;
    }

    private void addElement(Node element) {
        pane.getChildren().add(element);
    }
}
