package org.openjfx.Views;

import javafx.scene.layout.Pane;
import org.openjfx.Models.Character.Enemy.Boss;
import org.openjfx.Models.Game;
import org.openjfx.Models.Level.LevelBoss;

public class ViewLevelBoss extends View {
    private final LevelBoss level;
    private final Game game;

    public ViewLevelBoss(Pane pane, Game game) {
        super(pane);
        this.game = game;
        level = new LevelBoss(new Boss());
    }

    public LevelBoss getLevel() {
        return level;
    }
}