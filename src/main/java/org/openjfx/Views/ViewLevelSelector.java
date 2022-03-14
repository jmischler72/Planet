package org.openjfx.Views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import org.openjfx.Controllers.ControllerLevelSelector;
import org.openjfx.Models.LevelButton;
import org.openjfx.Models.LevelSelectorSubScene;
import org.openjfx.Models.World;


public class ViewLevelSelector extends Scene {
    private ControllerLevelSelector controllerLevelSelector;
    private ViewManager viewManager;

    public ViewLevelSelector(ViewManager viewManager) {
        super(viewManager.getMainPane(), viewManager.getSize()[0], viewManager.getSize()[1]);
        this.viewManager = viewManager;
        controllerLevelSelector = new ControllerLevelSelector(viewManager);

        World world = new World();
        LevelButton levelButton = new LevelButton(new Integer[]{20, 20});
        viewManager.getMainPane().getChildren().add(levelButton);
        LevelSelectorSubScene levelSelectorSubScene = new LevelSelectorSubScene();
        viewManager.getMainPane().getChildren().add(levelSelectorSubScene);
        levelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controllerLevelSelector.setBackground();
                levelSelectorSubScene.moveSubScene();
            }
        });


    }
}
