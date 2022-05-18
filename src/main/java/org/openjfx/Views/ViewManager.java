package org.openjfx.Views;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.openjfx.Models.Enemy.Enemy;
import org.openjfx.Models.Game;
import org.openjfx.Models.Level.LevelEnemy;

/*
    ViewManager permet de gerer et stocker les infos concernant le stage ainsi que la scene active
 */
public class ViewManager {

    private static final int HEIGHT = 900;
    private static final int WIDTH = 1500;
    private View activeView;
    private static Stage mainStage;
    private Game game;

    public ViewManager(Game game ){
        this.game = game;
        Enemy enemy = new Enemy();
        game.setCurrentLevel(new LevelEnemy(enemy));
        activeView = new ViewLevelEnemy(new Pane(), this);
        mainStage = new Stage();
        mainStage.setScene(activeView);
    }

    public Stage getMainStage(){
        return mainStage;
    }

    public Pane getActivePane(){ return activeView.getPane();}

    public int[] getSize(){
        return new int[]{WIDTH,HEIGHT};
    }

    public Game getGame() {
        return game;
    }

    public void renderView(View view){
        activeView.stopAnimations();
        activeView = view;
        mainStage.setScene(view);
    }
}
