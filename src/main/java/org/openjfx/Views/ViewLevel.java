package org.openjfx.Views;

import javafx.scene.layout.Pane;
import org.openjfx.Models.*;

public class ViewLevel extends View{

    protected Level level;

    public ViewLevel(Pane pane, ViewManager viewManager, PlanetType planetType) {
        super(pane, viewManager);
    }

    public Level getLevel() {
        return level;
    }
}
