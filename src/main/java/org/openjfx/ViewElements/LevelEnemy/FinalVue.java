package org.openjfx.ViewElements.LevelEnemy;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FinalVue extends StackPane {

    private ButtonMenu quitButton;

    public FinalVue(boolean isWin, double width, double height) {
        setPrefSize(width, height);
        setBackground(new Background(new BackgroundFill(new Color(0,0,0,0.6), null, null)));

        Label text;
        if (isWin) {
            text = new Label("You won");
            StackPane lootPane = new StackPane();
            lootPane.setAlignment(Pos.CENTER);
            lootPane.setMinSize(300, 100);
            lootPane.setMaxSize(700, 300);
            lootPane.setBackground(
                    new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(30), null))
            );

            Label lootText = new Label("You received : 500G");
            try {
                lootText.setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/main_font.ttf"), 30));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            lootPane.getChildren().add(lootText);
            getChildren().add(lootPane);
        } else {
            text = new Label("You loose");
        }

        try {
            text.setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/main_font.ttf"), 100));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setAlignment(text, Pos.TOP_CENTER);
        getChildren().add(text);

        quitButton = new ButtonMenu(new double[]{100, 50}, "quit");
        setAlignment(quitButton, Pos.BOTTOM_LEFT);

        getChildren().add(quitButton);
    }

    public ButtonMenu getQuitButton() {
        return quitButton;
    }
}
