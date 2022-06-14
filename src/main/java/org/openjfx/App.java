package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.openjfx.Models.Game;
import org.openjfx.Views.ViewHome;
import org.openjfx.Views.ViewLevelSelector;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Game game = new Game();
        ViewLevelSelector view = new ViewLevelSelector(new Pane(), game);
//        ViewHome view = new ViewHome(new Pane(), game);
        stage.setScene(view);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}