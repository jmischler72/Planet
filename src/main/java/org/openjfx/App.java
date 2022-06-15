package org.openjfx;

import javafx.application.Application;
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

    @Override
    public void start(Stage stage) throws IOException {

        /* TODO et fixes
        - on recup de la vie chaque fois quon revient dans le level
        - bug bizarre ou jai pas eu lecran win
        - les boutons disable( quon a win ) restent affiches quand on click sur un autre level
         */
        Game game = new Game();
//        ViewHome view = new ViewHome(new Pane(), game);
        ViewLevelSelector view = new ViewLevelSelector(game);
        stage.setScene(view);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}