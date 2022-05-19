package org.openjfx.Models.Level;

public class Item {

    private ShopItems name;
    private int damage;

    public Item(ShopItems itemName) {
        name = itemName;
    }

    public ShopItems getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }
}