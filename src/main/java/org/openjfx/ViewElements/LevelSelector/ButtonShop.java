package org.openjfx.ViewElements.LevelSelector;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.shape.Shape;

public class ButtonShop extends ButtonAnimation{

    public ButtonShop(double[] position, double[] size, Shape shape, String imageName) {
        super(position, size, shape, imageName);
        initAnimationListener();
    }

    private void initAnimationListener() {
        setOnMouseEntered(event -> {
            setEffect(new DropShadow());
            Image image = new Image(getClass().getResource("Infos_subscene_assets/shop_button_open.png").toExternalForm());
            BackgroundImage background = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
            setBackground(new Background(background));
        });

        setOnMouseExited(event -> {
            setEffect(null);
            Image image = new Image(getClass().getResource("Infos_subscene_assets/shop_button_close.png").toExternalForm());
            BackgroundImage background = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
            setBackground(new Background(background));
        });
    }
}
