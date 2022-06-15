package org.openjfx.Views;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import org.openjfx.Models.Game;

public class ViewHome extends View{
    Game game;

    public ViewHome( Game game) {
        super(new Pane());
        setBackgroundColor(Color.BLACK);

        this.game = game;

        render();
    }
    public void render(){
        MediaPlayer player = new MediaPlayer( new Media(getClass().getResource("planet_animation.avi").toExternalForm()));
        MediaView mediaView = new MediaView(player);
        mediaView.setFitHeight(HEIGHT);
        mediaView.setFitWidth(WIDTH);

        player.play();
        addElement(mediaView);
        player.setOnEndOfMedia(()->{
            ViewTransition transition = new ViewTransition(this, new ViewLevelSelector(game),game.getCurrentPlanet().getType().name());
            transition.render();
        });
    }

}
