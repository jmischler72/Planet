package org.openjfx.Views;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
    ViewManager permet de gerer et stocker les infos concernant le stage ainsi que la scene active
 */
public class ViewManager {

    private static final int HEIGHT = 900;
    private static final int WIDTH = 1500;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public ViewManager(Stage stage){
        mainPane = new AnchorPane();
        mainScene = new ViewLevelSelector(this);
        mainStage = stage;
        stage.setScene(mainScene);
    }



    public Stage getMainStage(){
        return mainStage;
    }

    public Pane getMainPane(){ return mainPane;}

    public int[] getSize(){
        return new int[]{WIDTH,HEIGHT};
    }
}
