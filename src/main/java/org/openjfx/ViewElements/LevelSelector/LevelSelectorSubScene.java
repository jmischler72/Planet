package org.openjfx.ViewElements.LevelSelector;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.openjfx.Models.Level.Level;
import org.openjfx.Models.Level.LevelType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LevelSelectorSubScene extends SubScene {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;

    private final Button button;


    public LevelSelectorSubScene(int buttonSize, double[] position, Level level) {
        /*size*/
        super(new AnchorPane(), WIDTH, HEIGHT);

        /*positions*/
        setLayoutX(position[0]);
        setLayoutY(position[1]);

        this.setOpacity(0);
        this.setDisable(true);

        Text text = new Text(100, 50, level.getName());
        try {
            text.setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/main_font.ttf"), 12));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        text.setFill(Color.WHITE);

        Rectangle cadre = new Rectangle(200, 20);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(cadre, text);
        stack.setLayoutX(WIDTH / 2 - cadre.getWidth() / 2);
        stack.setLayoutY(23);

        BackgroundImage background = new BackgroundImage(
                new Image(getClass().getResource("Infos_subscene_assets/infos_subscene_background.png").toExternalForm(), WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane infosRoot = (AnchorPane) this.getRoot();
        infosRoot.setBackground(new Background(background));

        double[] buttonPosition = new double[]{WIDTH/2 , HEIGHT - 60};

        ButtonSelector b = createButton(level, buttonPosition);
        button = b;

        addElement(stack);
        addElement(b);
    }

    private ButtonSelector createButton(Level level, double[] buttonPosition) {

        if(level.getType() == LevelType.Shop) {
            ButtonSelector b = new ButtonSelector(level, buttonPosition, new double[]{80, 40}, null, "Infos_subscene_assets/shop_button_close.png");

            b.setOnMouseEntered(event -> {
                b.setEffect(new DropShadow());
                Image image = new Image(getClass().getResource("Infos_subscene_assets/shop_button_open.png").toExternalForm());
                BackgroundImage background = new BackgroundImage(
                        image,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
                b.setBackground(new Background(background));
            });

            b.setOnMouseExited(event -> {
                b.setEffect(null);
                Image image = new Image(getClass().getResource("Infos_subscene_assets/shop_button_close.png").toExternalForm());
                BackgroundImage background = new BackgroundImage(
                        image,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
                b.setBackground(new Background(background));
            });

            return b;
        }

        return new ButtonSelector(level, buttonPosition, new double[]{80, 40}, null, "Infos_subscene_assets/fight_button.png");
    }

    public Button getButton() {
        return button;
    }

    public void moveSubScene() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(0.1));
        fade.setAutoReverse(true);
        fade.setNode(this);

        if (isDisable()) {
            fade.setFromValue(0);
            fade.setToValue(1);
            this.setDisable(false);
        } else {
            fade.setFromValue(1);
            fade.setToValue(0);
            this.setDisable(true);
        }
        fade.play();
    }

    public void addElement(Node element) {
        AnchorPane root = (AnchorPane) this.getRoot();
        root.getChildren().add(element);
    }

}

