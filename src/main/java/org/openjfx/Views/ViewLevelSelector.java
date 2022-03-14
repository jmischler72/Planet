package org.openjfx.Views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import org.openjfx.Controllers.ControllerLevelSelector;
import org.openjfx.Models.LevelButton;
import org.openjfx.Models.LevelSelectorSubScene;
import org.openjfx.Models.World;
import org.openjfx.Renderer.Noise;
import org.openjfx.Renderer.SimplexNoise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class ViewLevelSelector extends Scene {
    private ControllerLevelSelector controllerLevelSelector;
    private ViewManager viewManager;

    public ViewLevelSelector(ViewManager viewManager) {
        super(viewManager.getMainPane(), viewManager.getSize()[0], viewManager.getSize()[1]);
        this.viewManager = viewManager;
        controllerLevelSelector = new ControllerLevelSelector(viewManager);
        controllerLevelSelector.setBackground();


        World world = new World();


        Shadow shadow = new Shadow(BlurType.GAUSSIAN, Color.BLACK, 20);
        shadow.setHeight(80);
        shadow.setWidth(80);
        Circle circle = new Circle();
        circle.setCenterX(viewManager.getSize()[0] / 2);
        circle.setCenterY(viewManager.getSize()[1] / 2+10);
        circle.setRadius(viewManager.getSize()[1]*0.40);
        circle.setEffect(shadow);
        viewManager.getMainPane().getChildren().add(circle);

        Circle circle2 = new Circle();
        circle2.setCenterX(viewManager.getSize()[0] / 2);
        circle2.setCenterY(viewManager.getSize()[1] / 2);
        circle2.setRadius(viewManager.getSize()[1]*0.40);
        try {
            circle2.setFill(new ImagePattern(
                    new Image(new FileInputStream(world.getPlanet_file())), 0, 0, 1, 1, true

            ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        viewManager.getMainPane().getChildren().add(circle2);


        LevelButton levelButton = new LevelButton(new Integer[]{20, 20});
        viewManager.getMainPane().getChildren().add(levelButton);
        LevelSelectorSubScene levelSelectorSubScene = new LevelSelectorSubScene();
        viewManager.getMainPane().getChildren().add(levelSelectorSubScene);
        levelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                levelSelectorSubScene.moveSubScene();
            }
        });


    }
}
