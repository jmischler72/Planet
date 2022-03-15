package org.openjfx.ViewElements.LevelSelector;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LevelButton extends Button {

    private final String FONT = "";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('" +getClass().getResource("lvl_button_pressed.png").toExternalForm()+ "')";
    private final String BUTTON_RELEASED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('" +getClass().getResource("lvl_button_released.png").toExternalForm()+ "')";
    private Level level;
    private LevelInfosSubScene infos;
    private Boolean isInfoOpened;

    public LevelButton(int[] position) {
        super();
        this.level = null;
        this.isInfoOpened = false;
        this.infos = new LevelInfosSubScene();
        setShape(new Circle(10));
        setPrefWidth(56);
        setPrefHeight(56);
        setLayoutX(position[0]);
        setLayoutY(position[1]);
        setStyle(BUTTON_RELEASED_STYLE);
        initButtonListeners();
    }

    private void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT), 23));
        }
        catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setLayoutX(getLayoutX());
        setLayoutY(getLayoutY());
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_RELEASED_STYLE);
        setLayoutX(getLayoutX());
        setLayoutY(getLayoutY());
    }

    private void initButtonListeners() {
        setOnMousePressed(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
                showLevelInfos();
            }
        });

        setOnMouseReleased(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleasedStyle();
            }
        });

        setOnMouseEntered(event -> {
            setEffect(new DropShadow());
        });

        setOnMouseExited(event -> {
            setEffect(null);
        });
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    private void showLevelInfos() {
        if(isInfoOpened) {
            this.getChildren().remove(1);
            isInfoOpened = false;
            return;
        }

        this.getChildren().add(infos);
        isInfoOpened = true;
    }
}
