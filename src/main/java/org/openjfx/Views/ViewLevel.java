package org.openjfx.Views;

import javafx.scene.layout.Pane;
import org.openjfx.Models.*;
import org.openjfx.Models.Level.*;

public class ViewLevel extends View{

    private Level level;

    public ViewLevel(Pane pane,ViewManager viewManager, Level level ){
        super(pane, viewManager);
        setBackground("random_background.jpg");
        this.level = level;

    }

    public ViewLevel(Pane pane, ViewManager viewManager, PlanetType planetType, int levelCounter) {
        super(pane, viewManager);
        setBackground("random_background.jpg");

        if(levelCounter%5 == 0) level = new LevelBoss(planetType);
        else if(levelCounter%6 == 0) level = new LevelShop(planetType);
        else level = new LevelEnemy(planetType);


    }

    public ViewLevel(Pane pane, ViewManager viewManager, PlanetType planetType, LevelType levelType) {
        super(pane, viewManager);
        setBackground("random_background.jpg");

        switch (levelType) {
            case Enemy: level = new LevelEnemy(planetType);
            case Boss: level = new LevelBoss(planetType);
            case Shop: level = new LevelShop(planetType);
        }

    }

    public Level getLevel() {
        return level;
    }
}
