package org.openjfx.Views;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
    ViewManager permet de gerer et stocker les infos concernant le stage ainsi que la scene active
 */
public class ViewManager {

    private static final int HEIGHT = 900;
    private static final int WIDTH = 1500;
    private AnchorPane mainPane;
    private View activeScene;
    private static Stage mainStage;

    public ViewManager(Stage stage){
        activeScene = new ViewLevelSelector(new AnchorPane(), this);
        mainStage = stage;
        stage.setScene(activeScene);
    }

    public Stage getMainStage(){
        return mainStage;
    }

    public Pane getMainPane(){ return mainPane;}

    public int[] getSize(){
        return new int[]{WIDTH,HEIGHT};
    }

    public void renderScene(View scene){
        activeScene.stopAnimations();
        activeScene = scene;
        mainStage.setScene(scene);
    }

    public void reloadPane(){
        this.mainPane = new AnchorPane();
    }

}
