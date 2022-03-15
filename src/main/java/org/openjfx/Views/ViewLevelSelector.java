package org.openjfx.Views;

import javafx.event.ActionEvent;
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

import org.openjfx.Controllers.ControllerLevelSelector;
import org.openjfx.Models.Level;
import org.openjfx.ViewElements.LevelSelector.LevelButton;
import org.openjfx.ViewElements.LevelSelector.LevelSelectorSubScene;
import org.openjfx.Models.World;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class ViewLevelSelector extends Scene {
    private ControllerLevelSelector controllerLevelSelector;
    private Pane pane;

    public ViewLevelSelector(Pane pane, int width, int height, ViewManager viewManager) {
        super(pane, height, width);
        this.pane = pane;

        setBackground();

        World world = new World();

        Circle circleWorld = renderCircleWorld(world);
        Circle shadow = renderCircleShadow(circleWorld, 80, new int[]{0,10});
        this.addElement(shadow);
        this.addElement(circleWorld);

        for(int i=0; i< 1; i++){
            Level level = new Level();
            Random rand = new Random();
            int [] position = new int[2];

//            position[0] = rand.nextInt((int) circle2.getRadius()) + (int) circle2.getCenterX();
            position[0] =(int) ( circleWorld.getCenterX() -circleWorld.getRadius() );
            position[1] =(int) ( circleWorld.getCenterY() -circleWorld.getRadius() );
//            position[1] = rand.nextInt((int) circle2.getRadius()) + (int) circle2.getCenterY();

            LevelSelectorSubScene levelSelectorSubScene = new LevelSelectorSubScene(position);
            this.addElement(levelSelectorSubScene);

            LevelButton levelButton = new LevelButton(position);
            levelButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    viewManager.renderCurrentScene();
                    levelSelectorSubScene.moveSubScene();
                }
            });

            this.addElement(levelButton);
        }

    }

    private void setBackground(){
        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource("space.jpg").toExternalForm(),pane.getWidth(), pane.getHeight(), false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        pane.setBackground(new Background(background));
    }

    private Circle renderCircleWorld(World world){
        Circle circle = new Circle();
        circle.setCenterX(pane.getWidth() / 2);
        circle.setCenterY(pane.getHeight()/ 2);
        circle.setRadius(pane.getHeight()*0.40);
        try {
            circle.setFill(new ImagePattern(
                    new Image(new FileInputStream(world.getPlanet_file())), 0, 0, 1, 1, true

            ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return circle;
    }

    private Circle renderCircleShadow(Circle circle_to_shadow, int amount, int[] offset){
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

    private void addElement(Node element){
        pane.getChildren().add(element);
    }
}
