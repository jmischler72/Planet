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

}
