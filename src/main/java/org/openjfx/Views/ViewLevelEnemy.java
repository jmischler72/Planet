package org.openjfx.Views;

import javafx.scene.layout.Pane;
import org.openjfx.Models.Level.Level;
import org.openjfx.ViewElements.LevelEnemy.ButtonMenu;
import org.openjfx.ViewElements.LevelSelector.ButtonAnimation;

public class ViewLevelEnemy extends ViewLevel{

    public ViewLevelEnemy(Pane pane, ViewManager viewManager, Level level) {
        super(pane, viewManager, level);

        
        ButtonMenu b = new ButtonMenu( new double[]{80,viewManager.getSize()[1]-60}, new double[]{100,50}, null, "test");
        addElement(b);
    }

    public void test(){
        System.out.println("t");
    }
    

}
