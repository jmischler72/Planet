package org.openjfx.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.openjfx.Models.*;
import org.openjfx.Models.Level.Item;
import org.openjfx.Models.Level.ItemIcon;
import org.openjfx.Models.Level.LevelShop;

import java.util.ArrayList;

public class ViewLevelShop extends View{

    private static final int ITEM_AREA_SIZE = 640;
    private static final int ICON_SIZE = 140;
    private ArrayList<ItemIcon> icons = new ArrayList<>();
    private LevelShop level;

    public ViewLevelShop(Pane pane, ViewManager viewManager) {
        super(pane, viewManager);
        setBackground("shop_background.png");
        this.level = new LevelShop();
        createIcons();
    }

    private void createRectangle() {
        Rectangle rectangle = new Rectangle(ITEM_AREA_SIZE, ITEM_AREA_SIZE);
        rectangle.setLayoutX(132);
        rectangle.setLayoutY(132);
        addElement(rectangle);
    }

    private void createIcons() {
        ArrayList<Item> items = ((LevelShop) level).getItems();
        for(int i = 0; i < items.size(); i++) {
            double[] position = new double[] {(216 + i*(ICON_SIZE + 46)), 216};
            ItemIcon icon = new ItemIcon(position, new double[] {ICON_SIZE, ICON_SIZE}, new Rectangle(ICON_SIZE, ICON_SIZE), items.get(i).getName());
            icons.add(icon);
            addElement(icon);
        }
    }

    private void setIconAction() {
        for (ItemIcon item : icons) {
            item.setOnAction((event) -> {
                Player player = viewManager.getGame().getPlayer();
                level.buyItem();
            });
        }
    }

    public LevelShop getLevel() {
        return level;
    }
}
