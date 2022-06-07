package org.openjfx.ViewElements.LevelShop;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import org.openjfx.Models.Shop.Item;
import org.openjfx.ViewElements.ButtonAnimation;

public class ItemIcon extends ButtonAnimation {

    private final Item item;
    private Tooltip description;

    public ItemIcon(double[] position, double[]size, Shape shape, Item item) {
        super(position, size, shape);

        this.item = item;
        Image image = new Image(ItemIcon.class.getResource("item_border.png").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
        setBackground(new Background(background));
        setText(item.getName());

        createTooltip();
    }

    private void createTooltip() {
        description = new Tooltip();
        description.setOpacity(0.7);
        description.setShowDelay(new Duration(0.6));
        description.setHideDelay(new Duration(0.8));
        description.setPrefSize(200, 100);
        setTooltip(description);
    }

    public Item getItem() {
        return item;
    }

    public Tooltip getDescription() {
        return description;
    }
}