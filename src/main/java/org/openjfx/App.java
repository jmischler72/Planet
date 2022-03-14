package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.openjfx.Views.ViewManager;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage = new Stage(StageStyle.DECORATED);
        stage.getIcons().add(new Image(App.class.getResource("icon.jpg").toExternalForm()));
        stage.setTitle("Planet ! :)");
        stage.setResizable(false);
        ViewManager viewManager = new ViewManager(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}