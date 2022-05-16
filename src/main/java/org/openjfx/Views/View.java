package org.openjfx.Views;

import javafx.animation.Animation;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class View extends Scene {

    private ArrayList<Animation> animations = new ArrayList<Animation>();
    private Pane pane;
    ViewManager viewManager;

    public View(Pane pane, ViewManager viewManager) {
        super(pane, viewManager.getSize()[0], viewManager.getSize()[1]);
        this.pane = pane;
        this.viewManager = viewManager;
    }

    public void setBackground(String imageName) {
        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource(imageName).toExternalForm(), viewManager.getSize()[0], viewManager.getSize()[1], false, true),
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
    public ArrayList<Animation> getAnimations(){
        return animations;
    }

    public void addAnimation(Animation animation){
        animations.add(animation);
    }

    public void stopAnimations() {
        for (Animation animation : animations) {
            animation.stop();
        }
    }
}
