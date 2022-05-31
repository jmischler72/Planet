package org.openjfx.Models.Level;

import org.openjfx.Models.PlanetType;

import java.util.ArrayList;

public class LevelShop extends Level {

    private final ArrayList<Item> items = new ArrayList<Item>();

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