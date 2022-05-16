package org.openjfx.ViewElements.LevelShop;

import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.shape.Shape;
import org.openjfx.Models.Item;
import org.openjfx.Models.ShopItems;
import org.openjfx.ViewElements.ButtonAnimation;

public class ItemIcon extends Button {

    private ColorAdjust colorAdjust = new ColorAdjust();
    private Item item;
    private ItemDescriptionSubScene descriptionSubScene;

    public ItemIcon(double[] position, double[]size, Shape shape, ShopItems itemName) {
        super();
        setShape(shape);
        setPrefWidth(size[0]);
        setPrefHeight(size[1]);
        setLayoutX(position[0] - getPrefWidth() / 2);
        setLayoutY(position[1] - getPrefHeight() / 2);

        item = new Item(itemName);
        initButtonListeners();
        Image image = new Image(ItemIcon.class.getResource("item_border.png").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
        setBackground(new Background(background));
        setText(item.getName().toString());

        descriptionSubScene = new ItemDescriptionSubScene(this, item);
    }

    private void initButtonListeners() {
        setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                colorAdjust.setBrightness(-0.5);
                setEffect(colorAdjust);
            }
        });

        setOnMouseReleased(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                colorAdjust.setBrightness(0);
                setEffect(colorAdjust);

            }
        });

        setOnMouseEntered(event -> {
            setEffect(new DropShadow());
            this.getChildren().add(descriptionSubScene);
        });

        setOnMouseExited(event -> {
            setEffect(null);
            this.getChildren().remove(descriptionSubScene);
        });
    }
}
