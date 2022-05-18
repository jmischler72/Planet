package org.openjfx.Views;

import javafx.animation.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javafx.util.Duration;
import org.openjfx.Models.*;
import org.openjfx.ViewElements.ButtonAnimation;
import org.openjfx.ViewElements.LevelSelector.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import static javafx.util.Duration.millis;


public class ViewLevelSelector extends View {
    private Planet planet;
    private ArrayList<ButtonAnimation> buttonList  = new ArrayList<ButtonAnimation>();
    private ArrayList<View> levels = new ArrayList<>();

    public ViewLevelSelector(Pane pane, ViewManager viewManager) {
        super(pane, viewManager);
        setBackground("space.jpg");
        planet = new Planet();

        Circle circlePlanet = renderCirclePlanet(planet);
        Circle shadow = renderCircleShadow(circlePlanet, 80, new int[]{0, 0});
        renderShadowAnimation(circlePlanet,shadow);
        addElement(shadow);
        addElement(circlePlanet);
        createButtons(circlePlanet);
//
//        LevelButton refresh = new LevelButton(new int[]{20,20});
//        refresh.setOnAction((event) -> {    // lambda expression
//            viewManager.renderScene( new ViewLevelSelector( new AnchorPane(),viewManager));
//        });

//        addElement(refresh);
    }

    private void createButtons(Circle circlePlanet) {
        ArrayList<double[]> positions = new ArrayList<double[]>();

        for (int i = 0; i < 6; i++) {

            Circle circleMarkers = new Circle(circlePlanet.getCenterX(), circlePlanet.getCenterY(), circlePlanet.getRadius() - 80);
            double[] position = getRandomPositionInCircle(circleMarkers);

            boolean close = true;
            do {
                close = false;
                for (double[] pos : positions) {
                    if (arePositionsClose(pos, position, 40)) {
                        position = getRandomPositionInCircle(circleMarkers);
                        close = true;
                    }
                }
            } while (close);

            positions.add(position);

            Group levelGroup = new Group();
            LevelType levelType = null;

            if((i+1)%5 == 0) levelType = LevelType.Shop;
            else if((i+1)%6 == 0) levelType = LevelType.Boss;
            else levelType = LevelType.Enemy;

            String imageName;
            LevelSelectorSubScene levelSelectorSubScene;
            ButtonAnimation levelButton;
            /*
            switch (levelType) {
                case Boss:
                    imageName = "LevelSelector/lvl_button_boss.png";
                    ViewLevelBoss viewLevelBoss = new ViewLevelBoss(new Pane(), viewManager, planet.getType());
                    levelButton = new ButtonAnimation(position,new double[]{56,56}, new Circle(10), imageName);
                    levelSelectorSubScene = new LevelSelectorSubScene((int) levelButton.getPrefWidth(), position, viewLevelBoss.getLevel());
                    break;
                case Shop:
                    imageName = "LevelSelector/lvl_button_shop.png";
                    ViewLevelShop viewLevelShop = new ViewLevelShop(new Pane(), viewManager, planet.getType());
                    levelButton = new ButtonAnimation(position,new double[]{56,56}, new Circle(10), imageName);
                    levelSelectorSubScene = new LevelSelectorSubScene((int) levelButton.getPrefWidth(), position, viewLevelShop.getLevel());
                    break;
                case Enemy:
                    imageName = "LevelSelector/lvl_button.png";
                    ViewLevelEnemy viewLevelEnemy = new ViewLevelEnemy(new Pane(), viewManager, planet.getType());
                    levelButton = new ButtonAnimation(position,new double[]{56,56}, new Circle(10), imageName);
                    levelSelectorSubScene = new LevelSelectorSubScene((int) levelButton.getPrefWidth(), position, viewLevelEnemy.getLevel());
                    break;
                default:
                    imageName = "";
                    System.out.println("Level type not found");
            }

            levelButton.setOnAction((event) -> {
                if (levelSelectorSubScene.isDisable()) {
                    animationButtons(levelButton, false);
                } else {
                    animationButtons(levelButton, true);
                }
                levelSelectorSubScene.moveSubScene();
            });

            Button playButton = levelSelectorSubScene.getButton();
            playButton.setOnAction((playEvent) -> {
                viewManager.renderScene(viewLevel);
                planet.setCurrentLevel(viewLevel.getLevel());
            });

            addElement(levelSelectorSubScene);
            addElement(levelButton);
            levels.add(viewLevel);
            buttonList.add(levelButton);*/
        }
    }

    private void animationButtons(ButtonAnimation activeButton, boolean opened) {

        for (ButtonAnimation button : buttonList) {
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


    private boolean arePositionsClose(double[] position1, double[] position2, int radius) {
        if (Math.abs(position1[0] - position2[0]) < radius) {
            return true;
        }
        if (Math.abs(position1[1] - position2[1]) < radius) {
            return true;
        }
        return false;

    }

    /* Animation lag sur certains pc donc a voir pour l'ajouter */

    private void renderShadowAnimation(Circle planet, Circle shadow) {
        final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler() {
            int movingStep = 0;

            @Override
            public void handle(Event event) {
                System.out.println(movingStep);
                movingStep++;
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
        addAnimation(timeline);

    }


    private double[] getRandomPositionInCircle(Circle circle) {
        Random rand = new Random();
        double[] position = new double[2];

        double randomX = (rand.nextInt((int) (circle.getRadius() * 2)) - circle.getRadius());
        position[0] = circle.getCenterX() + randomX;

        double cosX = (randomX / (circle.getRadius()));
        double boundY = Math.round(Math.sin(Math.acos(cosX)) * circle.getRadius());

        double randomY = (rand.nextInt((int) (boundY) * 2 + 1) - boundY);
        position[1] = circle.getCenterY() + randomY;

        return position;
    }

    private Circle renderCirclePlanet(Planet planet) {
        Circle circle = new Circle();
        circle.setCenterX(getPane().getWidth() / 2);
        circle.setCenterY(getPane().getHeight() / 2);
        circle.setRadius(getPane().getHeight() * 0.40);
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
        circle.setCache(true);
        circle.setCacheHint(CacheHint.SPEED);
        return circle;
    }
}
