package org.openjfx.Controllers;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.openjfx.Views.ViewLevelSelector;
import org.openjfx.Views.ViewManager;

public class ControllerLevelSelector{

    private final ViewManager viewManager;

    public ControllerLevelSelector(ViewManager viewManager){
        this.viewManager = viewManager;
    }

    public void setBackground() {
        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource("green_planet.png").toExternalForm(), viewManager.getSize()[0], viewManager.getSize()[1], false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);


        viewManager.getMainPane().setBackground(new Background(background));
    }
}
