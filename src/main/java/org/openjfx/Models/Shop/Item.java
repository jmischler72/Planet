package org.openjfx.Models.Shop;

public abstract class Item {

    private long id;
    private String name;
    private long cost;
    private ItemType type;

    public Item(String itemName, long cost, ItemType type, long id) {
        this.id = id;
        name = itemName;
        this.cost = cost;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCost() {
        return cost;
    }

    public ItemType getType() {
        return type;
    }
}