package org.openjfx.Views;

import javafx.animation.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javafx.scene.text.Font;
import javafx.util.Duration;
import org.openjfx.Models.Game;
import org.openjfx.Models.Level.Level;
import org.openjfx.Models.PlanetType;
import org.openjfx.ViewElements.LevelEnemy.ButtonMenu;
import org.openjfx.ViewElements.LevelSelector.*;
import org.openjfx.Models.Planet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import static javafx.util.Duration.millis;


public class ViewLevelSelector extends View {
    private final Planet planet;

    private final Game game;
    private final ArrayList<ButtonSelector> buttonList = new ArrayList<ButtonSelector>();

    public ViewLevelSelector(Game game) {
        super(new Pane());
        setBackground("space.jpg");
        this.game = game;
        this.planet = game.getCurrentPlanet();


        Circle circlePlanet = ElementPlanet(planet);
        Circle shadow = ElementPlanetShadow(circlePlanet, 80, new int[]{0, 0});
        AnimationShadow(shadow);

        addElement(shadow);
        addElement(circlePlanet);


        createButtons(circlePlanet);
//        if (planet.getDoneLevels().size() >= planet.getLevels().size() - 1) {
//            nextPlanet = true;
//        }
        if(game.getCurrentPlanet().getDoneLevels().size() == game.getCurrentPlanet().getLevels().size()-1){
            addElement(ElementNextButton());

        }
        if(game.getPlanets().indexOf(game.getCurrentPlanet())>=1){
            addElement(ElementPreviousButton());
        }
    }

    private void createButtons(Circle circlePlanet) {
        ArrayList<double[]> positions = new ArrayList<double[]>();

        for (Level level : planet.getLevels()) {
            if (level.getPosition() == null) {

                boolean close = true;
                double[] position;
                do {
                    close = false;
                    position = getRandomPositionInCircle(new double[]{circlePlanet.getCenterX(), circlePlanet.getCenterY()}, circlePlanet.getRadius() - 80);

                    for (double[] pos : positions) {
                        if (arePositionsClose(pos, position, 40)) {
                            close = true;
                        }
                    }
                } while (close);

                level.setPosition(position);

                positions.add(position);
            }

            ButtonSelector levelButton = new ButtonSelector(level, new double[]{56, 56}, new Circle(10));
            LevelSelectorSubScene levelSelectorSubScene = new LevelSelectorSubScene(level.getPosition(), level);

            levelButton.setOnAction((event) -> {    // lambda expression
                AnimationButtons(levelButton, !levelSelectorSubScene.isDisable());
                levelSelectorSubScene.moveSubScene();
            });

            Button playButton = levelSelectorSubScene.getButton();
            playButton.setOnAction((playEvent) -> {
                game.setCurrentLevel(level);
                View levelView = null;
                switch (level.getType()) {
                    case Boss:
                        levelView = new ViewLevelBoss(game);
                        break;
                    case Shop:
                        levelView = new ViewLevelShop(game);
                        break;
                    case Enemy:
                        levelView = new ViewLevelEnemy(game);
                        break;
                }

                ViewTransition viewTransition = new ViewTransition(this, levelView, level.getName());
                viewTransition.render();

            });

            if (planet.getDoneLevels().contains(level)) {
                levelButton.setDisable(true);
            }

            addElement(levelSelectorSubScene);
            addElement(levelButton);
            buttonList.add(levelButton);
        }
    }


    private boolean arePositionsClose(double[] position1, double[] position2, int radius) {
        if (Math.abs(position1[0] - position2[0]) < radius) {
            return true;
        }
        return Math.abs(position1[1] - position2[1]) < radius;

    }

    private double[] getRandomPositionInCircle(double[] center, double radius) {
        Random rand = new Random();
        double[] position = new double[2];

        double randomX = (rand.nextInt((int) (radius * 2)) - radius);
        position[0] = center[0] + randomX;

        double cosX = (randomX / (radius));
        double boundY = Math.round(Math.sin(Math.acos(cosX)) * radius);

        double randomY = (rand.nextInt((int) (boundY) * 2 + 1) - boundY);
        position[1] = center[1] + randomY;

        return position;
    }

    private Circle ElementPlanet(Planet planet) {
        Circle circle = new Circle();
        circle.setCenterX(WIDTH / 2);
        circle.setCenterY(HEIGHT / 2);
        if (planet.getSize() == 0) {
            Random random = new Random();
            double size_planet = 0.30 + (double) random.nextInt(40 - 27) / 100;
            planet.setSize(size_planet);
        }

        circle.setRadius(HEIGHT * planet.getSize());
        try {
            circle.setFill(new ImagePattern(
                    new Image(new FileInputStream(planet.getPlanet_file())), 0, 0, 1, 1, true

            ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return circle;
    }

    private Circle ElementPlanetShadow(Circle circle_to_shadow, int amount, int[] offset) {
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

    private Button ElementNextButton() {
        ButtonMenu button = new ButtonMenu(new double[]{180, 50}, "Next planet");
        button.setLayoutX(WIDTH - 250);
        button.setLayoutY(HEIGHT / 2);
        try {
            button.setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/main_font.ttf"), 15));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        button.setOnAction((event) -> {
            if(game.getPlanets().indexOf(game.getCurrentPlanet())== game.getPlanets().size()-1){
                game.setCurrentPlanet(new Planet(new ArrayList<PlanetType>(){{add(PlanetType.DryTerran);}}));
                game.addPlanet(game.getCurrentPlanet());
            }else {
                game.setCurrentPlanet(game.getPlanets().get(game.getPlanets().indexOf(planet)+1));
            }

            ViewLevelSelector levelView = new ViewLevelSelector(game);
            ViewTransition viewTransition = new ViewTransition(this, levelView, planet.getType().name() + " " + planet.getSize());
            viewTransition.render();
        });

        return button;
    }
    private Button ElementPreviousButton() {
        ButtonMenu button = new ButtonMenu(new double[]{180, 50}, "prev planet");
        button.setLayoutX(WIDTH - 250);
        button.setLayoutY(HEIGHT / 2-100);
        try {
            button.setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/main_font.ttf"), 15));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        button.setOnAction((event) -> {
            game.setCurrentPlanet(game.getPlanets().get(game.getPlanets().indexOf(game.getCurrentPlanet())-1));
            ViewLevelSelector levelView = new ViewLevelSelector(game);
            ViewTransition viewTransition = new ViewTransition(this, levelView, planet.getType().name() + " " + planet.getSize());
            viewTransition.render();
        });

        return button;
    }

    private void AnimationShadow(Circle shadow) {
        final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler() {
            int movingStep = 0;

            @Override
            public void handle(Event event) {
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

    private void AnimationButtons(ButtonSelector activeButton, boolean opened) {

        for (ButtonSelector button : buttonList) {
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.seconds(0.1));
            fade.setAutoReverse(true);

            if (button != activeButton) {
                if (!opened) {
                    fade.setFromValue(1);
                    fade.setToValue(0);
                    button.setDisable(true);
                } else {
                    fade.setFromValue(0);
                    fade.setToValue(1);
                    if (!planet.getDoneLevels().contains(button.getLevel())) {
                        button.setDisable(false);
                    }
                }
                fade.setNode(button);
                fade.play();
            }
        }

    }
}
