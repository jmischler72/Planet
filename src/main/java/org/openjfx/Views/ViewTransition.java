package org.openjfx.Views;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ViewTransition extends View{

    public ViewTransition(Pane pane, ViewManager viewManager) {
        super(pane, viewManager);
    }

}
