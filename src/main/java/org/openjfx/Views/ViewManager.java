package org.openjfx.Views;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.Properties;

/*
    ViewManager permet de gerer et stocker les infos concernant le stage ainsi que la scene active
 */
public class ViewManager {

    private static final int HEIGHT = 900;
    private static final int WIDTH = 1500;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private static final String levelButtonPosFileName = "levelButtonPositions.properties";

    public ViewManager(){
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.getIcons().add(new Image(getClass().getResource("icon.jpg").toExternalForm()));
        stage.setTitle("Planet ! :)");
        stage.setResizable(false);

        mainPane = new AnchorPane();
        mainScene = new ViewLevelSelector(this);
        stage.setScene(mainScene);
        mainStage = stage;
    }

    public void setsScene(Scene scene) {
        this.mainStage.setScene(scene);
    }

    public String getLevelsPositionsFor(String type) throws IOException {
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();

            inputStream = getClass().getResourceAsStream(levelButtonPosFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + levelButtonPosFileName + "' not found in the classpath");
            }

            String posInString = prop.getProperty(type);
            return posInString;

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }

        return null;
    }

    public Stage getMainStage(){
        return mainStage;
    }

    public Pane getMainPane(){ return mainPane;}

    public int[] getSize(){
        return new int[]{WIDTH,HEIGHT};
    }
}
