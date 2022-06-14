package org.openjfx;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.openjfx.Models.Game;
import org.openjfx.Views.ViewLevelSelector;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Game game = new Game();
        ViewLevelSelector view = new ViewLevelSelector(new Pane(), game);
        stage.setScene(view);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}