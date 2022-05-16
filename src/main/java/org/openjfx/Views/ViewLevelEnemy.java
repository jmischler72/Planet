package org.openjfx.Views;

import javafx.scene.layout.Pane;
import org.openjfx.Models.LevelEnemy;
import org.openjfx.Models.LevelShop;
import org.openjfx.Models.PlanetType;

public class ViewLevelEnemy extends ViewLevel{
    public ViewLevelEnemy(Pane pane, ViewManager viewManager, PlanetType planetType) {
        super(pane, viewManager, planetType);
        level = new LevelEnemy(planetType);
    }
}
