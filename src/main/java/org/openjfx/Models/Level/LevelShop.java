package org.openjfx.Models.Level;

import org.openjfx.Models.Shop.Item;
import org.openjfx.Models.Shop.ShopItems;

import java.io.IOException;
import java.util.ArrayList;
import org.json.*;

public class LevelShop extends Level {

    private ArrayList<Item> items = new ArrayList<Item>();

    public LevelShop() {
        super("Marchand", LevelType.Shop);
        fetchItems();
    }

    private void fetchItems() {
        for(ShopItems item:ShopItems.values()) {
            items.add(new Item(item));
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public boolean buyItem() {
        return  true;
    }
}