package org.openjfx.ViewElements.LevelShop;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.openjfx.Models.Shop.Item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EquipedItem extends Button {
    private final Item item;
    private Tooltip description;

    public EquipedItem(Item item) {
        this.item = item;
        try {
            setFont(Font.loadFont(new FileInputStream("src/main/resources/org/openjfx/Views/Fonts/main_font.ttf"), 15));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setText(item.getName());
        setBackground(null);
        setBorder(new Border(new BorderStroke(Color.BLACK, new BorderStrokeStyle(StrokeType.CENTERED, null, null, 4, 4, null), new CornerRadii(10), new BorderWidths(2))));
        setPadding(new Insets(10));
        setLineSpacing(20);

        createTooltip();
    }

    private void createTooltip() {
        description = new Tooltip();
        description.setOpacity(0.7);
        description.setShowDelay(new Duration(0.6));
        description.setHideDelay(new Duration(0.8));
        description.setPrefSize(200, 110);
        setTooltip(description);
    }

    public Item getItem() {
        return item;
    }

    public Tooltip getDescription() {
        return description;
    }
}
