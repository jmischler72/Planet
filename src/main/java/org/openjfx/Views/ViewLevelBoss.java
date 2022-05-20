package org.openjfx.Views;

import javafx.scene.layout.Pane;
import org.openjfx.Models.Personage.Enemy.Boss;
import org.openjfx.Models.Level.LevelBoss;

public class ViewLevelBoss extends View {
    private LevelBoss level;

    public ViewLevelBoss(Pane pane, ViewManager viewManager) {
        super(pane, viewManager);
        level = new LevelBoss(new Boss());
    }

    public LevelBoss getLevel() {
        return level;
    }
}