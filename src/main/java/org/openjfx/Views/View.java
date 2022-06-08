package org.openjfx.Views;

import javafx.animation.Animation;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class View extends Scene {

    private final ArrayList<Animation> animations = new ArrayList<Animation>();
    private final Pane pane;
    protected static int HEIGHT = 900  ;
    protected static int WIDTH = 1500 ;

    public View(Pane pane) {
        super(pane, WIDTH, HEIGHT);
        this.pane = pane;
    }

    public void setBackground(String imageName) {
        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource(imageName).toExternalForm(), WIDTH,HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        pane.setBackground(new Background(background));
    }

    public void setBackgroundColor(Color color){
            pane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void addElement(Node element) {
        pane.getChildren().add(element);
    }

    public Pane getPane(){
        return pane;
    }

    public void addAnimation(Animation animation){
        animations.add(animation);
    }

    public void stopAnimations() {
        for (Animation animation : animations) {
            animation.stop();
        }
    }

    public void renderView(View view){
        stopAnimations();

        Stage stage = (Stage) this.getWindow();
        stage.setScene(view);
    }
}
