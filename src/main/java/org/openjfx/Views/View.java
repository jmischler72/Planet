package org.openjfx.Views;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

import static javafx.util.Duration.millis;

public class View extends Scene {

    private ArrayList<Animation> animations = new ArrayList<Animation>();
    private Pane pane;
    private ViewManager viewManager;


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
