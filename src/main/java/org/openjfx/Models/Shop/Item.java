package org.openjfx.Models.Shop;

public abstract class Item {

    private String name;
    private long cost;

    public Item(String itemName, long cost) {
        name = itemName;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public long getCost() {
        return cost;
    }
}