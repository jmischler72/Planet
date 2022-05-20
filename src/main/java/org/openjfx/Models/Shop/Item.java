package org.openjfx.Models.Shop;

public class Item {

    private ShopItems name;
    private int cost;

    public Item(ShopItems itemName) {
        name = itemName;
        cost = 200;
    }

    public ShopItems getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}