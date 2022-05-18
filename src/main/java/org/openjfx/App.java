package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.openjfx.Models.Game;
import org.openjfx.Views.ViewLevelSelector;
import org.openjfx.Views.ViewManager;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Game game = new Game();
        ViewManager viewManager = new ViewManager(game);
        stage = viewManager.getMainStage();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}