package org.openjfx.Models;

import org.openjfx.Models.Personnage.Player;

import java.util.ArrayList;

public class LevelShop extends Level{

    private ArrayList<Item> items = new ArrayList<Item>();

    public LevelShop(PlanetType planetType) {
        super(planetType);
        name = "Marchand quelconque";
        type = LevelType.Shop;
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
