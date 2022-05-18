package org.openjfx.Views;

import javafx.scene.layout.Pane;
import org.openjfx.Models.LevelBoss;
import org.openjfx.Models.PlanetType;

public class ViewLevelBoss extends View {
    private LevelBoss level;

    public ViewLevelBoss(Pane pane, ViewManager viewManager, PlanetType planetType) {
        super(pane, viewManager);
        level = new LevelBoss(planetType);
    }

    public LevelBoss getLevel() {
        return level;
    }
}
