package org.openjfx.Views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import org.openjfx.Controllers.ControllerLevelSelector;
import org.openjfx.Models.Level;
import org.openjfx.Models.LevelButton;
import org.openjfx.Models.LevelSelectorSubScene;
import org.openjfx.Models.World;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ViewLevelSelector extends Scene {

    private static final int NB_LVL_AREA = 6;
    private ControllerLevelSelector controllerLevelSelector;
    private ViewManager viewManager;
    private World world;
    private List<Level> levels;

    public ViewLevelSelector(ViewManager viewManager) {
        super(viewManager.getMainPane(), viewManager.getSize()[0], viewManager.getSize()[1]);
        this.viewManager = viewManager;
        controllerLevelSelector = new ControllerLevelSelector(viewManager);
        controllerLevelSelector.setBackground();


        world = new World();
        levels = new ArrayList<>();


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


        LevelButton levelButton = new LevelButton(new int[]{20, 20});
        viewManager.getMainPane().getChildren().add(levelButton);
        LevelSelectorSubScene levelSelectorSubScene = new LevelSelectorSubScene();
        viewManager.getMainPane().getChildren().add(levelSelectorSubScene);
        levelButton.setOnAction(actionEvent -> {

            levelSelectorSubScene.moveSubScene();
        });
        createLevels();
    }

    private void createLevels() {
        try {
            String[] positions = viewManager.getLevelsPositionsFor("DryTerran").split(",");
            for (int i=0; i<NB_LVL_AREA; i++) {
                Level level = new Level();
                String[] buttonPosInString = positions[i].split(";");
                LevelButton button = new LevelButton(
                        new int[] {Integer.parseInt(buttonPosInString[0]), Integer.parseInt(buttonPosInString[1])}
                );

                button.setLevel(level);

                viewManager.getMainPane().getChildren().add(button);
                levels.add(level);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
