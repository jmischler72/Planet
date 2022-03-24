package org.openjfx.Views;

import javafx.scene.layout.Pane;
import org.openjfx.Models.*;

public class ViewLevel extends View{

    private Level level;

    public ViewLevel(Pane pane, ViewManager viewManager, PlanetType planetType, LevelType levelType) {
        super(pane, viewManager);
        setBackground("random_background.jpg");

        switch (levelType) {
            case Enemy: level =
                    new LevelEnemy(planetType);
                break;
            case Boss: level =
                    new LevelBoss(planetType);
                break;
            case Shop: level = new LevelShop(planetType);
                break;
        }

    }

    public Level getLevel() {
        return level;
    }
}
