package org.openjfx.Views;

import javafx.scene.layout.Pane;
import org.openjfx.Models.LevelBoss;
import org.openjfx.Models.LevelShop;
import org.openjfx.Models.PlanetType;

public class ViewLevelBoss extends ViewLevel{
    public ViewLevelBoss(Pane pane, ViewManager viewManager, PlanetType planetType) {
        super(pane, viewManager, planetType);
        level = new LevelBoss(planetType);
    }
}
